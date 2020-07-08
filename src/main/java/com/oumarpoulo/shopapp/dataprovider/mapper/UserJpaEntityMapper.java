package com.oumarpoulo.shopapp.dataprovider.mapper;

import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.dataprovider.entity.UserJpaEntity;

public class UserJpaEntityMapper {

    public UserJpaEntity toUserJpaEntity(User user) {
        if (user == null) {
            return null;
        }
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setEmail(user.getEmail().getValue());
        userJpaEntity.setUsername(user.getUsername());
        return userJpaEntity;
    }
}
