package com.oumarpoulo.shopapp.core.entity;

import com.oumarpoulo.shopapp.core.exception.InvalidUserEmailException;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class UserEmail {
    private String value;

    public UserEmail(String value) {
        if (value.isBlank()) {
            throw new InvalidUserEmailException("user email is required");
        }
        if (!isEmail(value)) {
            throw new InvalidUserEmailException("user email format is invalid");
        }
        this.value = value;
    }

    private boolean isEmail(String email) {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches();
    }
}
