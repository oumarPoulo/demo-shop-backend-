package com.oumarpoulo.shopapp.core.usecase;

import com.oumarpoulo.shopapp.core.exception.*;
import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.core.entity.UserEmail;
import com.oumarpoulo.shopapp.core.gateway.UserRepository;

public class CreateUserUseCase implements UseCase<CreateUserRequest, User> {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserServiceException("unable to create user " + user.getUsername() + ": " + e.getMessage());
        }
    }

    private void checkUserEmailExists(CreateUserRequest createUserRequest) {
        if (userRepository.userEmailExists(createUserRequest.getEmail())) {
            throw new UserEmailExistsException("user with email " + createUserRequest.getEmail() + " already exists");
        }
    }

    private void checkUsernameExists(CreateUserRequest createUserRequest) {
        if (userRepository.usernameExists(createUserRequest.getUsername())) {
            throw new UserExistsException("username " + createUserRequest.getUsername() + " already exists");
        }
    }

    private void checkUserParams(CreateUserRequest createUserRequest) {
        if (createUserRequest.getUsername().isBlank()) {
            throw new InvalidUsernameException("username is required");
        }
    }

    @Override
    public User execute(CreateUserRequest input) {
        checkUserParams(input);
        checkUsernameExists(input);
        checkUserEmailExists(input);
        User user = new User(input.getUsername(), new UserEmail(input.getEmail()));
        return saveUser(user);
    }
}
