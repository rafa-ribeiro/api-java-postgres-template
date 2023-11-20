package com.java.meli.user.application.usecases;


import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.application.presenter.UserPresenter;
import com.java.meli.user.application.service.UserService;
import com.java.meli.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Transactional
public class UpdateUserUseCase {

    private final UserService userService;

    private final UserPresenter userPresenter;

    public UpdateUserUseCase(UserService userService, UserPresenter userPresenter) {
        this.userService = userService;
        this.userPresenter = userPresenter;
    }

    public UserOutputModel execute(UUID uuid, UserDTO userInputModel) {
        User user = this.userService.updateUserByUuid(uuid, userInputModel);
        return this.userPresenter.prepareSuccessView(user);
    }
}
