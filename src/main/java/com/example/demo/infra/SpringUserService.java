package com.example.demo.infra;

import com.example.demo.domain.UserConfig;
import com.example.demo.domain.UserRepository;
import com.example.demo.domain.UserService;
import com.example.demo.domain.event.EventService;
import org.springframework.stereotype.Service;

@Service
public class SpringUserService extends UserService {
    public SpringUserService(UserRepository userRepository, UserConfig userConfig, EventService eventService) {
        super(userRepository, userConfig, eventService);
    }
}
