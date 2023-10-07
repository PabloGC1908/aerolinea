package com.api.aerolinea.Mail;

import org.springframework.mail.SimpleMailMessage;


public class EmailService {


    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pguerracamana@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);


    }

}
