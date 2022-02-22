
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.optimus.appAdmin.domain.File;
import pl.optimus.appAdmin.domain.User;
import pl.optimus.appAdmin.repository.UserRepository;
import pl.optimus.appAdmin.response.ResponseFile;
import pl.optimus.appAdmin.response.ResponseMessage;
import pl.optimus.appAdmin.service.File.FileService;
import pl.optimus.appAdmin.service.email.EmailService;
import pl.optimus.appAdmin.service.user.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("api/v1")
public class FormController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final FileService fileService;

    public FormController(FileService fileService, UserRepository userRepository, UserService userService, EmailService emailService) {
        this.fileService = fileService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.emailService = emailService;

    }


    @GetMapping("/")
    public String formView(Model model) {
        model.addAttribute("userModel", new User());

        return "form";
    }

    @GetMapping("/userList")
    public String showListAllUsers(Model model) {
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("listUser", userList);
        return "userList";
    }

    @PostMapping("/newUser")
    public String saveNewUserAndSendEmail(@ModelAttribute User userModel, RedirectAttributes redirectAttr,
                                          @RequestParam("attachment") MultipartFile multipartFile,
                                          HttpServletRequest request) throws MessagingException {


       userService.saveUserSerialNumber(userModel,request);


        String message = "";
        try {
            fileService.store(multipartFile);
            message = "Uploaded the file successfully: " + multipartFile.getOriginalFilename();
        }catch (Exception e) {
            message = "Could not upload the file: " + multipartFile.getOriginalFilename() + "!";
        }
        userRepository.save(userModel);


        emailService.sendMessageToUser(userModel.getFirstName(), userModel.getEmail(), userModel.getContent());

        emailService.sendMessageToClient(userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(),
                userModel.getContent(), multipartFile);

        redirectAttr.addFlashAttribute("message", "The form has been sent");

        return "messageToUser";
    }



    @GetMapping("/files")
    public String getListFiles(Model model) {
        List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                    .path(dbFile.getId()).toUriString();

            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
        }).collect(Collectors.toList());

        model.addAttribute("fileList",files);
        return "fileList";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Object> getFile(@PathVariable String id) {

        try {
            File fileDB = fileService.getFile(id);

            // Response type: byte[]
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                    .body(fileDB.getData());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
        }
    }

    }



