package com.example.demo.domain;

import com.example.demo.domain.event.EventService;
import com.example.demo.domain.event.UserAdded;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;
    private final UserConfig userConfig;
    private final EventService eventService;

    public UserService(UserRepository userRepository, UserConfig userConfig, EventService eventService) {
        this.userRepository = userRepository;
        this.userConfig = userConfig;
        this.eventService = eventService;
    }

    public User add(String username) {
        if (userRepository.count() + 1 > userConfig.getMaxUsers()) {
            throw new IllegalStateException("Could not add more users");
        }

        var user = new User()
                .setId(UUID.randomUUID().toString())
                .setUsername(username);
        System.out.println("Storing user : " + user.getUsername());
        var storedUser = userRepository.store(user);

        eventService.publish(new UserAdded(storedUser.getUsername()));

        return storedUser;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}

