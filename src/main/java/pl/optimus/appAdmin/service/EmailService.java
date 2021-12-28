package pl.optimus.appAdmin.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessage(String firstName, String to, String text)throws MessagingException;
}
