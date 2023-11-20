package com.java.meli.user.application.models;

import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;
import java.util.Objects;

public class UserDTO {

    private JsonNullable<String> name;

    private JsonNullable<String> cpf;

    private JsonNullable<Date> birth;

    private JsonNullable<String> email;

    public UserDTO() {
    }

    public UserDTO(JsonNullable<String> name, JsonNullable<String> cpf, JsonNullable<Date> birth, JsonNullable<String> email) {
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.email = email;
    }

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getCpf() {
        return cpf;
    }

    public void setCpf(JsonNullable<String> cpf) {
        this.cpf = cpf;
    }

    public JsonNullable<Date> getBirth() {
        return birth;
    }

    public void setBirth(JsonNullable<Date> birth) {
        this.birth = birth;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(cpf, that.cpf) && Objects.equals(birth, that.birth) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, birth, email);
    }
}
