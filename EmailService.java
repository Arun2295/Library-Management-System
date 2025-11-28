package com.fitengineer.librarysystem.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendoverduemail(String to, String booktitle, String username){
        SimpleMailMessage msg  = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Library : Overdue mail message");
        msg.setText(String.format(
            "Hello %s,\n\nThe Book \"%s\" you rented  has been with you over  for over 7 days.\n" +
            "Please return it as soon as possible.\n\nThank you,\nLibrary system ",
            username != null ? username : "Reader", booktitle));

        mailSender.send(msg);
        

    }


}
