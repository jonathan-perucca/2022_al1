package com.example.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserBootstrap {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    void onStartup() {
        List.of(
                "John",
                "Smith",
                "Lea"
        ).forEach(username -> {
            userService.add(username);

            eventPublisher.publishEvent(new UserAdded().setName(username));
        });
    }
}

@Component
class OtherListener {

    @EventListener
    void on(UserAdded event) {
        System.out.println("Reacting on user added for user : " + event.getName());
    }
}

@Data
@Accessors(chain = true)
class UserAdded {
    private String name;
}
