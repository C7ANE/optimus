
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.optimus.appAdmin.domain.Register;
import pl.optimus.appAdmin.domain.UplodedFile;
import pl.optimus.appAdmin.repository.RegisterRepository;
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




    @PostMapping("/registrator")
    public String saveCandidate(@ModelAttribute Register  registerModel, RedirectAttributes redirectAttr,
                                @RequestParam("attachment")MultipartFile multipartFile,
                                @RequestParam("registerSerialNumber")int number,
                                HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {



        registerRepository.save( registerModel);

        redirectAttr.addFlashAttribute("message", "The form has been sent");
        log.info("create new candidate");
        log.error("Creating a new user failed ");

        emailService.sendMessageToUser( registerModel.getFirstName(), registerModel.getEmail(), registerModel.getContent());
        log.info("sent email to the user");
        log.error("Failed to send the message to the user");

        emailService.sendMessageToClient( registerModel.getFirstName(), registerModel.getLastName(), registerModel.getEmail(),
                 registerModel.getContent(),multipartFile);
        log.info("sent email to the Client");
        log.error("Failed to send the message to the Client");
        return "message";
    }



}

