package pl.optimus.appAdmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.optimus.appAdmin.domain.Candidate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMessageToUser(String firstName,String to, String text)throws MessagingException {
        MimeMessage messageToUser = javaMailSender.createMimeMessage();
        MimeMessageHelper mmHelperToUser = new MimeMessageHelper(messageToUser);
        mmHelperToUser.setTo(to);
        mmHelperToUser.setSubject("thanks for sending the application " + firstName);
        mmHelperToUser.setText(text);
        javaMailSender.send(messageToUser);
    }

    @Override
    public void sendMessageToClient(String firstName, String lastName, String email, String content) throws MessagingException {
        MimeMessage messageToClient = javaMailSender.createMimeMessage();
        MimeMessageHelper mmHelperToClinet = new MimeMessageHelper(messageToClient);

        mmHelperToClinet.setTo("zurok18@gmail.com");

        String fullName = firstName + lastName;

        String mailSubject =  fullName + " has sent the completed form";
        String mailContent = "Sender name: " + fullName + "\n";
        mailContent += "Sender Email: " + email + "\n";
        mailContent += "a message from to user" + "\n" + "\n";
        mailContent += content;

        mmHelperToClinet.setSubject(mailSubject);
        mmHelperToClinet.setText(mailContent);
        javaMailSender.send(messageToClient);
    }

}
