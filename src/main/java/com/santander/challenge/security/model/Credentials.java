package com.santander.challenge.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author guillermovarelli
 */
public class Credentials {

    private String username;
    private String password;
    private String token;
    @JsonIgnore
    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}