package com.java.meli.user.infrastructure.database;

import com.java.meli.user.application.dao.UserDAO;
import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.application.models.UserInputModel;
import com.java.meli.user.domain.User;
import com.java.meli.user.infrastructure.database.mapper.UserMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDAOImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(UserInputModel userInputModel) {
        UserEntity user = new UserEntity(
                userInputModel.getName(),
                userInputModel.getCpf(),
                userInputModel.getBirth(),
                userInputModel.getEmail());

        return this.userRepository.save(user);
    }

    @Override
    public Iterable<? extends User> listAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return this.userRepository.findAll(name, pageable);
    }

    @Override
    public User getByUuid(UUID uuid) {
        return this.userRepository.getByUuid(uuid);
    }

    @Override
    public User update(UUID uuid, UserDTO userDTO) throws DataIntegrityViolationException {
        UserEntity user = this.userRepository.getByUuid(uuid);
        this.userMapper.update(userDTO, user);
        return this.userRepository.save(user);
    }
}
