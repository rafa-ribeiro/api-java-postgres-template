package com.java.meli.user.application.usecases;

import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.presenter.UserPresenter;
import com.java.meli.user.application.service.UserService;
import com.java.meli.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListAllUsersUseCase {

    private final UserService userService;

    private final UserPresenter userPresenter;

    public ListAllUsersUseCase(UserService userService, UserPresenter userPresenter) {
        this.userService = userService;
        this.userPresenter = userPresenter;
    }

    public Iterable<UserOutputModel> execute(String name, int page, int size) {
        Iterable<? extends User> users = this.userService.listAll(name, page, size);
        List<UserOutputModel> output_users = new ArrayList<>();
        for (User user : users) {
            output_users.add(this.userPresenter.prepareSuccessView(user));
        }
        return output_users;
    }
}
