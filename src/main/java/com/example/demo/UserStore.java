package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class UserStore {

    private final List<String> db;

    UserStore(List<String> db) {
        this.db = db;
    }

    public void store(String username) {
        db.add(username);
    }

    public int count() {
        return db.size();
    }

    public List<String> findAll() {
        return new ArrayList<>(db);
    }
}
