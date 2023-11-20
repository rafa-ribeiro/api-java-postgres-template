package com.java.meli.user.domain;

import java.time.LocalDate;
import java.util.UUID;


public interface User {

    UUID getUuid();

    String getName();

    void setName(String name);

    String getCpf();

    void setCpf(String cpf);

    LocalDate getBirth();

    void setBirth(LocalDate birth);

    String getEmail();

    void setEmail(String email);
}
