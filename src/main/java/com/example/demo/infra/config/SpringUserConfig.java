package com.example.demo.infra.config;

import com.example.demo.domain.UserConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class SpringUserConfig implements UserConfig {
    private int maxUsers;

    public int getMaxUsers() {
        return maxUsers;
    }
    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }
}

