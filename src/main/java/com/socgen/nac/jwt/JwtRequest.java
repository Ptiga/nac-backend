package com.socgen.nac.jwt;

public class JwtRequest {

    String login;
    String password;

/*
    public JwtRequest() {
    }
*/
    public JwtRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

