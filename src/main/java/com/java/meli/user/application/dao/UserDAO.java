package com.java.meli.user.application.dao;

import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.application.models.UserInputModel;
import com.java.meli.user.domain.User;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

public interface UserDAO {

    User save(UserInputModel userInputModel);

    Iterable<? extends User> listAll(String name, int page, int size);

    User getByUuid(UUID uuid);

    User update(UUID uuid, UserDTO userDTO) throws DataIntegrityViolationException;
}
