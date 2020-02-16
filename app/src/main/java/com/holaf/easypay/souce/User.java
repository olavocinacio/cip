package com.holaf.easypay.souce;

import java.io.Serializable;

public class User implements Serializable {


    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static interface CONFIG {
        int MIN_LEN_PASSWORD = 6;
    }

    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String cpf;
    private String number;

    public String getName() {
        return name;
    }

    public User(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
