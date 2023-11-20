package com.java.meli.user.application.service;

import com.java.meli.user.application.models.UserInputModel;
import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.domain.User;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

public interface UserService {

    User createUser(UserInputModel inputUser) throws DataIntegrityViolationException;

    Iterable<? extends User> listAll(String name, int page, int size);

    User getByUuid(UUID uuid);

    User updateUserByUuid(UUID uuid, UserDTO userInputModel);

}
