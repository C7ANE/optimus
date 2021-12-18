package pl.optimus.appAdmin.service;

import javax.mail.MessagingException;

public interface EmailService {


    public void sendMessage(String to, String subject, String text, boolean isHtmlContent)throws MessagingException;
}
