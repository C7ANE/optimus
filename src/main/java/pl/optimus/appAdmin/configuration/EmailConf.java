package pl.optimus.appAdmin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailConf {

    @Bean
    public JavaMailSender getJavaMailSenderImpl(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("worktestmail1992@gmail.com");
        javaMailSender.setPassword("Zdkg5haz33");
        javaMailSender.setPort(587);

        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.starttles.enable",true);
        mailProperties.put("mail.smtp.ssl.trust","smtp.gmail.com");

        javaMailSender.setJavaMailProperties(mailProperties);

        return javaMailSender;
    }


}
