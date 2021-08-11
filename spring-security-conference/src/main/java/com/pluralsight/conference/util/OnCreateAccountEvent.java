package com.pluralsight.conference.util;

import org.springframework.context.ApplicationEvent;

import com.pluralsight.conference.model.Account;

public class OnCreateAccountEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1501095592399214968L;
	private String appUrl;
    private Account account;

    public OnCreateAccountEvent(Account account, String appUrl) {
        super(account);

        this.account = account;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Account getAccount() {
        return account;
    }
}
