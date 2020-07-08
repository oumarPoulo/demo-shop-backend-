package com.oumarpoulo.shopapp.dataprovider.mapper;

import com.oumarpoulo.shopapp.core.entity.User;
import com.oumarpoulo.shopapp.core.entity.UserEmail;
import com.oumarpoulo.shopapp.dataprovider.entity.UserJpaEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserJpaEntityMapperTest {

    private UserJpaEntityMapper userJpaEntityMapper;

    @Before
    public void setUp() {
        userJpaEntityMapper = new UserJpaEntityMapper();
    }

    @Test
    public void shouldReturnNullIfUserIsNotDefined() {
        UserJpaEntity userJpaEntity = userJpaEntityMapper.toUserJpaEntity(null);
        assertThat(userJpaEntity).isNull();
    }

    @Test
    public void shouldReturnUserJpaEntity() {
        User user = new User("ayoub", new UserEmail("ayoub@altran.com"));
        UserJpaEntity userJpaEntity = userJpaEntityMapper.toUserJpaEntity(user);
        assertThat(userJpaEntity).isNotNull();
        assertThat(userJpaEntity.getEmail()).isEqualTo(user.getEmail().getValue());
        assertThat(userJpaEntity.getUsername()).isEqualTo(user.getUsername());
    }



}
