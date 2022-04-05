package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserStore {

    private final Map<String, User> db;

    UserStore(Map<String, User> db) {
        this.db = db;
    }

    public User store(User user) {
        db.put(user.getId(), user);
        return user;
    }

    public int count() {
        return db.size();
    }

    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }
}
