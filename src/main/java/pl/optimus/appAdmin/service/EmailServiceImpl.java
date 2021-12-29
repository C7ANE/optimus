package pl.optimus.appAdmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
        MimeMessageHelper mmHelperToClinet = new MimeMessageHelper(messageToClient,true,"UTF-8");


        mmHelperToClinet.setTo("zurok18@gmail.com");
        mmHelperToClinet.setFrom("worktestmail1992@gmail.com");
        String fullName = firstName + lastName;

        String mailSubject =  fullName + " has sent the completed form";
        String mailContent ="<p><b> Sender name: </b>" + fullName + "</p>";
        mailContent += "<p><b>Sender Email: </b>" + email + "</p>";
        mailContent += "<p><b>A message from to user </b>" + "<br>";
        mailContent += content + "<hr><img src='cid:myLogo' />";


        mmHelperToClinet.addInline("myLogo",new ClassPathResource("/static/image/logo.png"));

        mmHelperToClinet.setSubject(mailSubject);
        mmHelperToClinet.setText(mailContent,true);
        javaMailSender.send(messageToClient);
    }

}
