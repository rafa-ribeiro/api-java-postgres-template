package com.java.meli.user.presentation.api.v1;


import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.application.models.UserInputModel;
import com.java.meli.user.application.models.UserOutputModel;
import com.java.meli.user.application.usecases.CreateUserUseCase;
import com.java.meli.user.application.usecases.GetUserByUuidUseCase;
import com.java.meli.user.application.usecases.ListAllUsersUseCase;
import com.java.meli.user.application.usecases.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private static final String DEFAULT_FILTER_VALUE = "{\n" +
            "<br> &nbsp;&nbsp;&nbsp; \"name\": \"Rafael\",\n" +
            "<br> &nbsp;&nbsp;&nbsp; \"page\": 0,\n" +
            "<br> &nbsp;&nbsp;&nbsp;  \"size\": 10\n" +
            "<br>}";
    private static final String FILTER_PARAM_DESCRIPTION = "Optional parameter that wraps some filter choices like: page, size and name. <br>" +
            "<br> - Page (Integer) indicating the page number. " +
            "<br> - Size (Integer) indicating the total of results per page." +
            "<br> - Name (String) indicating the value used for filtering the query results. Example: <br> " + DEFAULT_FILTER_VALUE;

    private static final String UPDATE_USER_BODY_DESCRIPTION = "The body request must be a valid JSON and following some rules: <br>" +
            "<br> - It can contains all Users attributes that should be updated" +
            "<br> - Omitted attributes will be ignored on update flow" +
            "<br> - Attributes updated with null values must be explicitly";


    private final CreateUserUseCase createUserUseCase;
    private final ListAllUsersUseCase listAllUsersUseCase;
    private final GetUserByUuidUseCase getUserByUuidUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserRestController(CreateUserUseCase createUserUseCase, ListAllUsersUseCase listAllUsersUseCase,
                              GetUserByUuidUseCase getUserByUuidUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listAllUsersUseCase = listAllUsersUseCase;
        this.getUserByUuidUseCase = getUserByUuidUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @Operation(summary = "List all Users with pagination")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "All users",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputModel.class))})
    )
    @GetMapping("")
    public Iterable<UserOutputModel> getUsers(@Parameter(description = FILTER_PARAM_DESCRIPTION) @RequestParam(defaultValue = "{}") HashMap<String, String> filters) {
        String name = filters.get("name");
        int page = Integer.parseInt(filters.getOrDefault("page", "0"));
        int size = Integer.parseInt(filters.getOrDefault("size", "10"));
        return this.listAllUsersUseCase.execute(name, page, size);
    }

    @Operation(summary = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The created user",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputModel.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error on creating new user",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict data on creating new user",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("")
    public UserOutputModel create(@RequestBody UserInputModel userInputModel) {
        return this.createUserUseCase.execute(userInputModel);
    }

    @Operation(summary = "Get a specific user by its uuid attribute")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "The user found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputModel.class))})
    )
    @GetMapping("/{uuid}")
    public UserOutputModel getUserByUuid(@PathVariable UUID uuid) {
        return this.getUserByUuidUseCase.execute(uuid);
    }

    @Operation(summary = "Update an existent User")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "The updated user",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputModel.class))})
    )
    @PatchMapping("/{uuid}")
    public UserOutputModel updateByUuid(@PathVariable UUID uuid, @Parameter(description = UPDATE_USER_BODY_DESCRIPTION) @RequestBody UserDTO userDTO) {
        return this.updateUserUseCase.execute(uuid, userDTO);
    }

}
