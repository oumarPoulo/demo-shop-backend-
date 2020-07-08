package com.oumarpoulo.shopapp.configuration;

import com.oumarpoulo.shopapp.core.gateway.UserRepository;
import com.oumarpoulo.shopapp.core.usecase.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }
}
