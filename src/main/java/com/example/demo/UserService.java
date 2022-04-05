package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserService {
    private final UserStore userStore;
    private final UserConfig userConfig;

    public UserService(UserStore userStore, UserConfig userConfig) {
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    public User add(String username) {
        if (userStore.count() + 1 > userConfig.getMaxUsers()) {
            throw new IllegalStateException("Could not add more users");
        }

        var user = new User()
                .setId(UUID.randomUUID().toString())
                .setUsername(username);
        System.out.println("Storing user : " + user.getUsername());
        return userStore.store(user);
    }

    public List<User> getUsers() {
        return userStore.findAll();
    }
}

