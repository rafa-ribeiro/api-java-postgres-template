package com.java.meli.user.infrastructure.database;

import com.java.meli.core.utils.DateUtils;
import com.java.meli.user.domain.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(
        name = "MeliUser",
        indexes = {
                @Index(name = "idx_user_uuid", columnList = "uuid", unique = true),
                @Index(name = "idx_user_cpf", columnList = "cpf", unique = true),
                @Index(name = "idx_user_email", columnList = "email", unique = true),
        }
)
public class UserEntity implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private UUID uuid;

    private String name;

    private String cpf;

    private java.sql.Date birth;

    private String email;

    public UserEntity() {
    }

    public UserEntity(String name, String cpf, Date birth, String email) {
        this.name = name;
        this.cpf = cpf;
        this.birth = this.convertToSQLDate(birth);
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirth() {
        return birth;
    }

    @Override
    public void setBirth(Date birth) {
        this.birth = this.convertToSQLDate(birth);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {

    }

    @PrePersist
    public void initUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    private java.sql.Date convertToSQLDate(Date originalDate) {
        return DateUtils.convertToSQLDate(originalDate);
    }
}
