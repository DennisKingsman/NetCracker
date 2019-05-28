package com.example.auth.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserForm {

    private String name;

    private String email;

    private String password;

    private String doublePassword;

    public UserForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDoublePassword() {
        return doublePassword;
    }

    public void setDoublePassword(String doublePassword) {
        this.doublePassword = doublePassword;
    }
}
