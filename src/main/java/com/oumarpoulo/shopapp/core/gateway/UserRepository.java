package com.oumarpoulo.shopapp.core.gateway;

import com.oumarpoulo.shopapp.core.entity.User;

public interface UserRepository {
    User save(User user);

    boolean userEmailExists(String email);

    boolean usernameExists(String username);
}
