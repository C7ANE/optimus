package pl.optimus.appAdmin.service.email;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageToUser(String firstName, String to, String text)throws MessagingException;


    void sendMessageToClient(String firstName, String lastName, String email, String content, MultipartFile multipartFile) throws MessagingException;
}

