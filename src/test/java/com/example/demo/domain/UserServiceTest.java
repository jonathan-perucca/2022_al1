package com.example.demo.domain;

import com.example.demo.domain.UserService;
import com.example.demo.infra.config.SpringUserConfig;
import com.example.demo.infra.event.NoOpEventService;
import com.example.demo.infra.repository.InMemoryUserStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    UserService userService;
    InMemoryUserStore inMemoryUserStore;
    SpringUserConfig userConfig;

    @BeforeEach
    void setup() {
        inMemoryUserStore = new InMemoryUserStore(new HashMap<>());
        userConfig = new SpringUserConfig();
        userConfig.setMaxUsers(2);

        userService = new UserService(inMemoryUserStore, userConfig, new NoOpEventService());
    }

    @Test
    void should_add_user() {
        userService.add("testy");

        var users = userService.getUsers();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUsername()).isEqualTo("testy");
    }

    @Test
    void should_throw_exception_when_too_many_users_reached() {
        userConfig.setMaxUsers(1);
        userService.add("testy");

        assertThatThrownBy(() -> userService.add("antoine"))
                .isInstanceOf(IllegalStateException.class);
    }
}