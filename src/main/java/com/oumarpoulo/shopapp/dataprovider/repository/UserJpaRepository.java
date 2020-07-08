package com.oumarpoulo.shopapp.dataprovider.repository;

import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.core.gateway.UserRepository;
import com.oumarpoulo.shopapp.dataprovider.entity.UserJpaEntity;
import com.oumarpoulo.shopapp.dataprovider.mapper.UserJpaEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
interface UserJpaProvider extends JpaRepository<UserJpaEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

@Component
public class UserJpaRepository implements UserRepository {
    private final UserJpaProvider userJpaProvider;
    private final UserJpaEntityMapper userJpaEntityMapper = new UserJpaEntityMapper();

    public UserJpaRepository(UserJpaProvider userJpaProvider) {
        this.userJpaProvider = userJpaProvider;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = userJpaEntityMapper.toUserJpaEntity(user);
        UserJpaEntity jpaEntity = userJpaProvider.save(entity);
        System.out.println(jpaEntity.getId());
        return user;
    }

    @Override
    public boolean userEmailExists(String email) {
        return userJpaProvider.existsByEmail(email);
    }

    @Override
    public boolean usernameExists(String username) {
        return userJpaProvider.existsByUsername(username);
    }
}
