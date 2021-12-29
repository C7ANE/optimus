package pl.optimus.appAdmin.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageToUser(String firstName, String to, String text)throws MessagingException;

    void sendMessageToClient(String firstName, String lastName, String email, String content) throws MessagingException;
}
