package com.example.demo;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    UserService userService;
    UserStore userStore;
    UserConfig userConfig;

    @BeforeEach
    void setup() {
        userStore = new UserStore(new ArrayList<>());
        userConfig = new UserConfig();
        userConfig.setMaxUsers(2);
        userService = new UserService(userStore, userConfig);
    }

    @Test
    void should_add_user() {
        userService.add("testy");

        var users = userService.getUsers();
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo("testy");
    }

    @Test
    void should_throw_exception_when_too_many_users_reached() {
        userConfig.setMaxUsers(1);
        userService.add("testy");

        assertThatThrownBy(() -> userService.add("antoine"))
                .isInstanceOf(IllegalStateException.class);
    }
}