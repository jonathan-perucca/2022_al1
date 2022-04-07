package com.example.demo.infra.repository;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity()
                .setId(user.getId())
                .setUsername(user.getUsername());
    }

    public User toModel(UserEntity userEntity) {
        return new User()
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername());
    }
}
