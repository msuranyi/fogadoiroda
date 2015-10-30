package hu.gdf.oop.fogadoiroda.web.model;

import org.hibernate.validator.constraints.Email;

public class Profile {

    private String username;

    private String oldPassword;

    private String newPassword;

    private String newPasswordCheck;

    private String email;

    public Profile() {
    }

    public Profile(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void clear() {
        oldPassword = null;
        newPassword = null;
        newPasswordCheck = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
