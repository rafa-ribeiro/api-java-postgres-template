package com.java.meli.user.presentation.api.v1;

import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.presenter.UserPresenter;
import com.java.meli.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class UserResponseFormatter implements UserPresenter {
    @Override
    public UserOutputModel prepareSuccessView(User user) {
        return new UserOutputModel(user.getUuid(), user.getName(), user.getCpf(), user.getBirth(), user.getEmail());
    }

    @Override
    public UserOutputModel prepareFailView(HttpStatus status, String error) throws ResponseStatusException {
        throw new ResponseStatusException(status, error);
    }

}
