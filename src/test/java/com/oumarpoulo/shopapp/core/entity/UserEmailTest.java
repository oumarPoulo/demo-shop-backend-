package com.oumarpoulo.shopapp.core.entity;

import com.oumarpoulo.shopapp.core.exception.InvalidUserEmailException;
import com.oumarpoulo.shopapp.core.entity.UserEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class UserEmailTest {
    @Test
    public void shouldThrowAnExceptionEmailInvalid() {
        assertThatThrownBy(() -> new UserEmail(""))
                .isInstanceOf(InvalidUserEmailException.class)
                .hasMessageContaining("user email is required");
    }

    @Test
    public void shouldThrowAnExceptionWhenInvalidEmail() {
        assertThatThrownBy(() -> new UserEmail("ayoubaltran.com"))
                .isInstanceOf(InvalidUserEmailException.class)
                .hasMessageContaining("user email format is invalid");
    }
}
