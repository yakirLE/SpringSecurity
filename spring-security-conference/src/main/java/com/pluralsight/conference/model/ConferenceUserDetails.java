package com.pluralsight.conference.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ConferenceUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -9178900713585930578L;
	private String nickname;

    public ConferenceUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
