package hu.gdf.oop.fogadoiroda.data.entity;

import java.time.LocalDateTime;

public class User {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String authority;

    private boolean active;

    private Integer balance;

    private LocalDateTime created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Object[] toArray(){
        Object[] array = {id,username,password,email,authority,active,balance,created};
        return array;
    }

    public static Object[] getEmptyModel(){
        return new Object[8];
    }

}