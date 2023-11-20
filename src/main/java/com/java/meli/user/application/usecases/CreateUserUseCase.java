package com.java.meli.user.application.usecases;

import com.java.meli.core.utils.cpf.CPFValidator;
import com.java.meli.core.utils.cpf.exceptions.CPFValidatorException;
import com.java.meli.user.application.models.UserInputModel;
import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.presenter.UserPresenter;
import com.java.meli.user.application.service.UserAgeService;
import com.java.meli.user.application.service.UserService;
import com.java.meli.user.domain.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;


@Component
public class CreateUserUseCase {

    public static final int MINIMUM_REQUIRED_AGE = 18;

    private final UserService userService;

    private final UserPresenter userPresenter;

    public CreateUserUseCase(UserService userService, UserPresenter userPresenter) {
        this.userService = userService;
        this.userPresenter = userPresenter;

    }

    public UserOutputModel execute(UserInputModel userInputModel) {
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userInputModel.getBirth(), MINIMUM_REQUIRED_AGE);
        if (!hasMinimumAge) {
            this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, "User is not the minimum required age");
        }

        boolean isValidCPF = false;
        try {
            isValidCPF = CPFValidator.isValidCPF(userInputModel.getCpf());
        } catch (CPFValidatorException e) {
            String error = MessageFormat.format("CPF validation error: {0}", e.getMessage());
            this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, error);
        }

        if (!isValidCPF) {
            this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, "The CPF is not valid");
        }

        try {
            User user = this.userService.createUser(userInputModel);
            return this.userPresenter.prepareSuccessView(user);
        } catch (DataIntegrityViolationException ex) {
            return this.userPresenter.prepareFailView(HttpStatus.CONFLICT, "Error on creating user.");
        }
    }
}
