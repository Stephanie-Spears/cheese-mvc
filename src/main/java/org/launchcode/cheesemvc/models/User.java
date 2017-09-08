package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private int userID;
    private static int nextID = 1;

    public User (String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User () {
        userID = nextID;
        nextID++;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}