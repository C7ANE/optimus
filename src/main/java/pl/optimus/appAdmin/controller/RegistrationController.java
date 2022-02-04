
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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




    @PostMapping("/registrator")
    public String saveNewRegisterAndSendEmail(@ModelAttribute Register  registerModel, RedirectAttributes redirectAttr,
                                @RequestParam("attachment")MultipartFile multipartFile,
                                HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {


        String[] registerSerialNumbers = request.getParameterValues("registerSerialNumber");
        for (int i = 0; i < registerSerialNumbers.length; i++) {
            registerModel.addDetails(registerSerialNumbers[i]);
        }


        uploadFileService.uploadToLocal(multipartFile);
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

//    @PostMapping("/upload/db")
//    public UploadFileResponse uploadDb(@RequestParam("file")MultipartFile multipartFile)
//    {
//        UplodedFile uploadFile = uploadFileService.uploadToDb(multipartFile);
//        UploadFileResponse response = new UploadFileResponse();
//        if(uploadFile != null){
//            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("api/v1/download/")
//                    .path(uploadFile.getFileId())
//                    .toUriString();
//
//            response.setDownloadUri(downloadUri);
//            response.setFileId(uploadFile.getFileId());
//            response.setFileType(uploadFile.getFileType());
//            response.setUploadStatus(true);
//            response.setMessage("File upload Successfully");
//            return response;
//        }
//        response.setMessage("Oops 1 something went wrong please re-upload.");
//        return response;
//    }



}

