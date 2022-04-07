package com.example.demo.infra.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserStore implements UserRepository {

    private final Map<String, User> db;

    public InMemoryUserStore(Map<String, User> db) {
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

    @Override
    public void deleteAll() {
        db.clear();
    }
}
