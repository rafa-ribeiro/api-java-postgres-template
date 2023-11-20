package com.java.meli.user.application.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class UserInputModel {

    private String name;

    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String email;

    public UserInputModel() {
    }

    public UserInputModel(String name, String cpf, LocalDate birth, String email) {
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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
