package com.java.meli.user.application.usecases;

import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.presenter.UserPresenter;
import com.java.meli.user.application.service.UserService;
import com.java.meli.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.UUID;

@Component
public class GetUserByUuidUseCase {

    private final UserService userService;

    private final UserPresenter userPresenter;

    public GetUserByUuidUseCase(UserService userService, UserPresenter userPresenter) {
        this.userService = userService;
        this.userPresenter = userPresenter;
    }

    public UserOutputModel execute(UUID user_uuid) {
        User user = this.userService.getByUuid(user_uuid);
        if (user != null) {
            return this.userPresenter.prepareSuccessView(user);
        } else {
            String error = MessageFormat.format("User not found by uuid = {0}", user_uuid);
            return this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, error);
        }

    }
}
