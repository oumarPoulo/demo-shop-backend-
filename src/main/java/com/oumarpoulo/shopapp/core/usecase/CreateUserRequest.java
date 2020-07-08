package com.oumarpoulo.shopapp.core.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequest {
    private String username;
    private String email;
}

// RegisterUser = (créer user, creer token, newsletter)
