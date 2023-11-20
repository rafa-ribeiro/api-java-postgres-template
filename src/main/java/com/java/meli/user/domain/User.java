package com.java.meli.user.domain;

import java.util.Date;
import java.util.UUID;


public interface User {

    UUID getUuid();

    String getName();

    void setName(String name);

    String getCpf();

    void setCpf(String cpf);

    Date getBirth();

    void setBirth(Date birth);

    String getEmail();

    void setEmail(String email);
}
