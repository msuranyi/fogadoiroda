package hu.gdf.oop.fogadoiroda.web.controller;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class Registration {

    private String username;

    private String password;

    private String passwordCheck;

    private String email;

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
