package com.oliveira.budget.domain.auth;

public class Auth {

    private String token;

    public Auth(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
