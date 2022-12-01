package com.mabanque.nac.jwt;

public class JwtResponse {

    //TODO: Ajouter les rôles

    private String login;
    private String role;


    public JwtResponse(String login) {
        this.login = login;
    }


    public JwtResponse(String login, String role) {
        this.login = login;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

