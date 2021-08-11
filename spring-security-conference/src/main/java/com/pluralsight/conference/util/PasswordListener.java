package com.pluralsight.conference.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.service.PasswordService;

@Component
public class PasswordListener implements ApplicationListener<OnPasswordResetEvent> {

	@Value("${app.baseURL}")
    private String serverUrl;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordService passwordService;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        this.resetPassword(event);
    }

    private void resetPassword(OnPasswordResetEvent event) {
        //create password token
        Password password = event.getPassword();
        String token = UUID.randomUUID().toString();
        passwordService.createResetToken(password, token);
        //get email properties
        String recipientAddress = password.getEmail();
        String subject = "Reset Password";
        String confirmationUrl = event.getAppUrl() + "/passwordReset?token=" + token;
        String message = "Reset password:";
        //send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + serverUrl + confirmationUrl);
        System.out.println(message + "\r\n" + serverUrl + confirmationUrl);
//        mailSender.send(email);
    }
}
