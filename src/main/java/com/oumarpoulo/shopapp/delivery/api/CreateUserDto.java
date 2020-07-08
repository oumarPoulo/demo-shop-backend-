package com.oumarpoulo.shopapp.delivery.api;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDto {
    @NotNull(message = "username cannot be null")
    private String username;

    @NotNull(message = "email cannot be null")
    private String email;
}
