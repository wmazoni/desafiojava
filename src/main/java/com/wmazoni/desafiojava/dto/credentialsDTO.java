package com.wmazoni.desafiojava.dto;

import java.io.Serializable;

public class credentialsDTO implements Serializable {
    private String email;
    private String password;

    public credentialsDTO() {
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
