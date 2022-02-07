
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.optimus.appAdmin.domain.Register;
import pl.optimus.appAdmin.domain.UplodedFile;
import pl.optimus.appAdmin.repository.RegisterRepository;
import pl.optimus.appAdmin.response.UploadFileResponse;
import pl.optimus.appAdmin.service.UploadFile.UploadFileService;
import pl.optimus.appAdmin.service.email.EmailService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequestMapping("api/v1")
public class RegistrationController {

    private final RegisterRepository registerRepository;
    private final EmailService emailService;
    private final UploadFileService uploadFileService;
    public RegistrationController(RegisterRepository registerRepository, EmailService emailService, UploadFileService uploadFileService) {
        this.registerRepository = registerRepository;
        this.emailService = emailService;
        this.uploadFileService = uploadFileService;
    }



    @GetMapping("/listRegister")
    public String showListToAllRegister(Model model){
        Iterable<Register> registerList = registerRepository.findAll();
        model.addAttribute("listRegister",registerList);
        return "register_information";
    }

    @PostMapping("/registrator")
    public String saveNewRegisterAndSendEmail(@ModelAttribute Register  registerModel,
                                              @ModelAttribute UplodedFile uplodedFile,
                                              RedirectAttributes redirectAttr,
                                              @RequestParam("attachment")MultipartFile multipartFile,
                                              HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {


        String[] registerSerialNumbers = request.getParameterValues("registerSerialNumber");
        for (int i = 0; i < registerSerialNumbers.length; i++) {
            registerModel.addDetails(registerSerialNumbers[i]);
        }


        uploadFileService.uploadToLocal(multipartFile);
        registerRepository.save( registerModel);


        UplodedFile uploaadfile = uploadFileService.uploadToDb(multipartFile);
        UploadFileResponse response = new UploadFileResponse();
        if(uploaadfile != null){
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1//registrator")
                    .path(uploaadfile.getFileId())
                    .toUriString();

            response.setDownloadUri(downloadUri);
            response.setFileId(uplodedFile.getFileId());
            response.setFileType(uplodedFile.getFileType());
            response.setUploadStatus(true);
            response.setMessage("File upload Successfully");
        }


        redirectAttr.addFlashAttribute("message", "The form has been sent");



        emailService.sendMessageToUser( registerModel.getFirstName(), registerModel.getEmail(), registerModel.getContent());



        emailService.sendMessageToClient( registerModel.getFirstName(), registerModel.getLastName(), registerModel.getEmail(),
                 registerModel.getContent(),multipartFile);
      


        return "message";
    }




}

