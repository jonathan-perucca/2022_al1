package com.example.demo.infra.bootstrap;

import com.example.demo.domain.event.UserAdded;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserListener {

    @EventListener
    public void onStartup(UserAdded event) {
        log.info("{} was added in store", event.getName());
    }
}