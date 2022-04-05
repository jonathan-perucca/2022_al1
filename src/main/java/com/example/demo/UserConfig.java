package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class UserConfig {
    private int maxUsers;

    public int getMaxUsers() {
        return maxUsers;
    }
    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }
}
