package com.oumarpoulo.shopapp.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private UserEmail email;
}
