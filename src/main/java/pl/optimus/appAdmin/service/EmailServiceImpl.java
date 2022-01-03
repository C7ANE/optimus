package pl.optimus.appAdmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.optimus.appAdmin.domain.Candidate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;

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
        MimeMessageHelper mimeHelperToUser = new MimeMessageHelper(messageToUser);
        mimeHelperToUser.setTo(to);
        mimeHelperToUser.setSubject("thanks for sending the application " + firstName);
        mimeHelperToUser.setText(text);
        javaMailSender.send(messageToUser);
    }

    @Override
    public void sendMessageToClient(String firstName, String lastName, String email, String content, MultipartFile multipartFile) throws MessagingException {
        MimeMessage messageToClient = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeHelperToClient = new MimeMessageHelper(messageToClient,true,"UTF-8");


        mimeHelperToClient.setTo("zurok18@gmail.com");
        mimeHelperToClient.setFrom("worktestmail1992@gmail.com");
        String fullName = firstName + lastName;

        String mailSubject =  fullName + " has sent the completed form";
        String mailContent ="<p><b> Sender name: </b>" + fullName + "</p>";
        mailContent += "<p><b>Sender Email: </b>" + email + "</p>";
        mailContent += "<p><b>A message from to user </b>" + "<br>";
        mailContent += content + "<hr><img src='cid:myLogo' />";

        ClassPathResource resource = new ClassPathResource("/static/image/logo.png");
        mimeHelperToClient.addInline("myLogo",resource);

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            InputStreamSource source = new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return multipartFile.getInputStream();
                }
            };

            mimeHelperToClient.addAttachment(fileName,source);
        }


        mimeHelperToClient.setSubject(mailSubject);
        mimeHelperToClient.setText(mailContent,true);
        javaMailSender.send(messageToClient);
    }

}
