package com.pluralsight.conference.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;

@Component
public class AccountListener implements ApplicationListener<OnCreateAccountEvent> {

	@Value("${app.baseURL}")
    private String serverUrl;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AccountService accountService;

    @Override
    public void onApplicationEvent(OnCreateAccountEvent event) {
        this.confirmCreateAccount(event);
    }

    private void confirmCreateAccount(OnCreateAccountEvent event) {
        //get the account
        //create verification token
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(account, token);
        //get email properties
        String recipientAddress = account.getEmail();
        String subject = "Account Confirmation";
        String confirmationUrl = event.getAppUrl() + "/accountConfirm?token=" + token;
        String message = "Please confirm:";
        //send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + serverUrl + confirmationUrl);
        System.out.println(message + "\r\n" + serverUrl + confirmationUrl);
//        mailSender.send(email);
    }
}
