package com.java.meli.user.application.usecases;


import com.java.meli.core.utils.cpf.CPFValidator;
import com.java.meli.core.utils.cpf.exceptions.CPFValidatorException;
import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.presenter.UserPresenter;
import com.java.meli.user.application.service.UserAgeService;
import com.java.meli.user.application.service.UserService;
import com.java.meli.user.domain.User;
import jakarta.transaction.Transactional;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDate;
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
        JsonNullable<LocalDate> birth = userInputModel.getBirth();
        if (birth != null) {
            boolean hasMinimumAge = UserAgeService.hasMinimumAge(birth.get(), CreateUserUseCase.MINIMUM_REQUIRED_AGE);
            if (!hasMinimumAge) {
                this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, "User is not the minimum required age");
            }
        }

        JsonNullable<String> cpf = userInputModel.getCpf();
        if (cpf != null) {
            boolean isValidCPF = false;
            try {
                isValidCPF = CPFValidator.isValidCPF(cpf.get());
            } catch (CPFValidatorException e) {
                String error = MessageFormat.format("CPF validation error: {0}", e.getMessage());
                this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, error);
            }

            if (!isValidCPF) {
                this.userPresenter.prepareFailView(HttpStatus.BAD_REQUEST, "The CPF is not valid");
            }
        }

        try {
            User user = this.userService.updateUserByUuid(uuid, userInputModel);
            return this.userPresenter.prepareSuccessView(user);
        } catch (Exception ex) {
            return this.userPresenter.prepareFailView(HttpStatus.CONFLICT, "Error on updating user.");
        }
    }
}
