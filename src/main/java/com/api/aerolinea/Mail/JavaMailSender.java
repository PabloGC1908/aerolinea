package com.api.aerolinea.Mail;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;


public class JavaMailSender {

    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("stmp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("pguerracamana@gmail.com");
        mailSender.setPassword("guerra2350");

        Properties properties = mailSender.getJavaMailProperties();

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
