package com.java.meli.user.application.models;

import java.util.Date;


public class UserInputModel {

    private String name;

    private String cpf;

    private Date birth;

    private String email;

    public UserInputModel() {
    }

    public UserInputModel(String name, String cpf, Date birth, String email) {
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
