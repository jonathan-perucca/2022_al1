package com.example.demo;

import java.util.List;

public class UserService {
    private final UserStore userStore;
    private final UserConfig userConfig;

    public UserService(UserStore userStore, UserConfig userConfig) {
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    public void add(String username) {
        if (userStore.count() + 1 > userConfig.getMaxUsers()) {
            throw new IllegalStateException("Could not add more users");
        }

        userStore.store(username);
        System.out.println("Stored " + username);
    }

    public List<String> getUsers() {
        return userStore.findAll();
    }
}

