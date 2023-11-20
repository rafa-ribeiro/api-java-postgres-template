package com.java.meli.user.application.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class UserOutputModel extends UserInputModel {

    private final UUID user_uuid;

    public UserOutputModel(UUID user_uuid, String name, String cpf, LocalDate birth, String email) {
        super(name, cpf, birth, email);
        this.user_uuid = user_uuid;
    }

    public UUID getUser_uuid() {
        return user_uuid;
    }
}
