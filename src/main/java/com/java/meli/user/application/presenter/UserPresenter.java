package com.java.meli.user.application.presenter;

import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserPresenter {

    UserOutputModel prepareSuccessView(User user);

    UserOutputModel prepareFailView(HttpStatus status, String error) throws ResponseStatusException;
}
