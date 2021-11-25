package pl.optimus.appAdmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.optimus.appAdmin.service.EmailService;

import javax.mail.MessagingException;

@Controller
public class MailController {

    private EmailService emailService;


    @Autowired

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendMail")
    public String sendMail()throws MessagingException{
        emailService.sendMessage("martyna.cywinskaa@gmail.com","wygrałaś","Udało ci się udać wygrać masaż o godzinie 11 40 w sypialni ",true);
        return "wyslano" ;
    }
}
