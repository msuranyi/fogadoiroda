package hu.gdf.oop.fogadoiroda.model;

import hu.gdf.oop.fogadoiroda.web.controller.Registration;

import java.util.Arrays;
import java.util.List;

public class User {

    private String username;

    private String password;

    private String email;

    private List<String> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String... roles) {
        this(username, password);
        if (roles != null) {
            this.roles = Arrays.asList(roles);
        }
    }

    public User(String username, String password, List<String> roles) {
        this(username, password);
        this.roles = roles;
    }

    public User(Registration registration, String... roles) {
        this(registration.getUsername(), registration.getPassword());
        if (roles != null) {
            this.roles = Arrays.asList(roles);
        }
        this.email = registration.getEmail();
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
