package com.java.meli.user.application.service.impl;

import com.java.meli.user.application.dao.UserDAO;
import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.application.models.UserInputModel;
import com.java.meli.user.application.service.UserService;
import com.java.meli.user.domain.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User createUser(UserInputModel inputUser) throws DataIntegrityViolationException {
        return this.userDAO.save(inputUser);
    }

    @Override
    public Iterable<? extends User> listAll(String name, int page, int size) {
        return this.userDAO.listAll(name, page, size);
    }

    @Override
    public User getByUuid(UUID uuid) {
        return this.userDAO.getByUuid(uuid);
    }

    @Override
    public User updateUserByUuid(UUID uuid, UserDTO userDTO) {
        return this.userDAO.update(uuid, userDTO);
    }
}