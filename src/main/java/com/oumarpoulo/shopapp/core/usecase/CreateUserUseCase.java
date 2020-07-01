package com.oumarpoulo.shopapp.core.usecase;

import com.oumarpoulo.shopapp.core.dto.UserDto;
import com.oumarpoulo.shopapp.core.exception.*;
import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.core.entity.UserEmail;
import com.oumarpoulo.shopapp.core.gateway.UserRepository;

public class CreateUserUseCase implements UseCase<UserDto, User> {
    private UserRepository userRepository;

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

    private void checkUserEmailExists(UserDto userDto) {
        if (userRepository.userEmailExists(userDto.getEmail())) {
            throw new UserEmailExistsException("user with email " + userDto.getEmail() + " already exists");
        }
    }

    private void checkUsernameExists(UserDto userDto) {
        if (userRepository.usernameExists(userDto.getUsername())) {
            throw new UserExistsException("username " + userDto.getUsername() + " already exists");
        }
    }

    private void checkUserParams(UserDto userDto) {
        if (userDto.getUsername().isBlank()) {
            throw new InvalidUsernameException("username is required");
        }
    }

    @Override
    public User execute(UserDto input) {
        checkUserParams(input);
        checkUsernameExists(input);
        checkUserEmailExists(input);
        User user = new User(input.getUsername(), new UserEmail(input.getEmail()));
        return saveUser(user);
    }
}
