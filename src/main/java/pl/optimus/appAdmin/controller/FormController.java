
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.optimus.appAdmin.domain.User;
import pl.optimus.appAdmin.repository.UserRepository;
import pl.optimus.appAdmin.service.UploadFile.UploadFileService;
import pl.optimus.appAdmin.service.email.EmailService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequestMapping("api/v1")
public class FormController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UploadFileService uploadFileService;
    public FormController(UserRepository userRepository, EmailService emailService, UploadFileService uploadFileService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.uploadFileService = uploadFileService;
    }




    @GetMapping("/")
    public String formView(Model model){
        model.addAttribute("userModel",new User());

        return "form";
    }
    @GetMapping("/userList")
    public String showListAllUsers(Model model){
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("listUser",userList);
        return"userList";
    }

    @PostMapping("/newUser")
    public String saveNewUserAndSendEmail(@ModelAttribute User userModel, RedirectAttributes redirectAttr,
                                              @RequestParam("attachment")MultipartFile multipartFile,
                                              HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {


        String[] userSerialNumbers = request.getParameterValues("userSerialNumber");
        for (String userSerialNumber : userSerialNumbers) {
            userModel.addDetails(userSerialNumber);
        }



        uploadFileService.uploadToLocal(multipartFile);

        userRepository.save(userModel);

        redirectAttr.addFlashAttribute("message", "The form has been sent");

        emailService.sendMessageToUser( userModel.getFirstName(), userModel.getEmail(), userModel.getContent());

        emailService.sendMessageToClient( userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(),
                 userModel.getContent(),multipartFile);
      
        return "messageToUser";
    }





}

