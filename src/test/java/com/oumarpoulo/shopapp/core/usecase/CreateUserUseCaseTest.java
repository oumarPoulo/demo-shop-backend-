package com.oumarpoulo.shopapp.core.usecase;


import com.oumarpoulo.shopapp.core.dto.UserDto;
import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.core.entity.UserEmail;
import com.oumarpoulo.shopapp.core.exception.*;
import com.oumarpoulo.shopapp.core.gateway.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    private CreateUserUseCase createUserUseCase;

    @Before
    public void setUp() {
        createUserUseCase = new CreateUserUseCase(userRepository);
    }

    @Test
    public void shouldThrowAnExceptionWhenUsernameIsEmpty() {
        UserDto userDto = new UserDto("", "ayoub@altran.com");
        assertThatThrownBy(() -> createUserUseCase.execute(userDto))
                .isInstanceOf(InvalidUsernameException.class)
                .hasMessageContaining("username is required");
    }

    @Test
    public void shouldThrowAnExceptionWhenUsernameExists() {
        String username = "Ayoub";
        UserDto userDto = new UserDto(username, "ayoub@altran.com");
        Mockito.when(userRepository.usernameExists(username)).thenReturn(true);
        assertThatThrownBy(() -> createUserUseCase.execute(userDto))
                .isInstanceOf(UserExistsException.class)
                .hasMessageContaining("username Ayoub already exists");
    }

    @Test
    public void shouldThrowAnExceptionWhenUserEmailInvalid() {
        String username = "Ayoub";
        String email = "";
        UserDto userDto = new UserDto(username, email);

        assertThatThrownBy(() -> createUserUseCase.execute(userDto))
                .isInstanceOf(InvalidUserEmailException.class)
                .hasMessageContaining("user email is required");
    }

    @Test
    public void shouldThrowAnExceptionWhenUserEmailExists() {
        String username = "Ayoub";
        String email = "ayoub@altran.com";
        UserDto userDto = new UserDto(username, email);

        Mockito.when(userRepository.usernameExists(username)).thenReturn(false);
        Mockito.when(userRepository.userEmailExists(email)).thenReturn(true);

        assertThatThrownBy(() -> createUserUseCase.execute(userDto))
                .isInstanceOf(UserEmailExistsException.class)
                .hasMessageContaining("user with email ayoub@altran.com already exists");
    }

    @Test
    public void shouldThrowAnExceptionWhenInvalidEmail() {
        String username = "Ayoub";
        String email = "ayoubaltran.com";
        UserDto userDto = new UserDto(username, email);

        assertThatThrownBy(() -> createUserUseCase.execute(userDto))
                .isInstanceOf(InvalidUserEmailException.class)
                .hasMessageContaining("user email format is invalid");
    }

    @Test
    public void shouldThrowAnExceptionWhenUserCreationFailed() {
        String username = "Ayoub";
        String email = "ayoub@altran.com";
        UserDto userDto = new UserDto(username, email);
        Mockito.when(userRepository.save(new User(username, new UserEmail(email)))).thenThrow(new RuntimeException("repository error"));
        assertThatThrownBy(() -> createUserUseCase.execute(userDto))
                .isInstanceOf(UserServiceException.class)
                .hasMessageContaining("unable to create user " + username);
    }

    @Test
    public void shouldCreateUser() {
        // given
        String username = "Ayoub";
        String email = "ayoub@altran.com";
        UserDto userDto = new UserDto(username, email);

        // when
        Mockito.when(userRepository.usernameExists(username)).thenReturn(false);
        Mockito.when(userRepository.userEmailExists(email)).thenReturn(false);
        Mockito.when(userRepository.save(new User(username, new UserEmail(email)))).thenReturn(new User(username, new UserEmail(email)));

        User user = createUserUseCase.execute(userDto);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail().getValue()).isEqualTo(email);
    }


}
