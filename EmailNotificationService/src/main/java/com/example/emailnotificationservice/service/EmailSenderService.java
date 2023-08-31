package com.example.emailnotificationservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String userEmail , String orderNumber){

        String subject = "Order Created: " + orderNumber;
        String text = "Dear Customer,\n\nYour order with order number " + orderNumber + " has been created.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("applicationshopping69@gmail.com");
        mailMessage.setTo(userEmail);
        mailMessage.setText(text);
        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);
    }
}
