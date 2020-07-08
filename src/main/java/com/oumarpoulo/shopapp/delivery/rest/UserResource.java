package com.oumarpoulo.shopapp.delivery.rest;

import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.core.usecase.CreateUserRequest;
import com.oumarpoulo.shopapp.core.usecase.CreateUserUseCase;
import com.oumarpoulo.shopapp.core.usecase.UseCaseExecutor;
import com.oumarpoulo.shopapp.delivery.api.CreateUserDto;
import com.oumarpoulo.shopapp.delivery.api.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

class UserResourceMapper {
    public CreateUserRequest createUserDtoToCreateUserRequest(CreateUserDto request) {
        if (request == null) {
            return null;
        }
        return new CreateUserRequest(request.getUsername(), request.getEmail());
    }


    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getUsername(), user.getEmail().getValue());
    }
}


@RestController
public class UserResource {

    private UseCaseExecutor useCaseExecutor;
    private CreateUserUseCase createUserUseCase;
    private final UserResourceMapper userResourceMapper = new UserResourceMapper();

    public UserResource(UseCaseExecutor useCaseExecutor, CreateUserUseCase createUserUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/api/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<UserDto> createUser(@Valid @RequestBody CreateUserDto request) {
        return useCaseExecutor.execute(
                createUserUseCase,
                userResourceMapper.createUserDtoToCreateUserRequest(request),
                (userResourceMapper::userToUserDto)

        );
    }

    @PostMapping("/api/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<UserDto> updateUser(@Valid @RequestBody CreateUserDto request) {
        return useCaseExecutor.execute(
                createUserUseCase,
                userResourceMapper.createUserDtoToCreateUserRequest(request),
                (userResourceMapper::userToUserDto)

        );
    }

}
