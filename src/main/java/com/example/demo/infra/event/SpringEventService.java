package com.example.demo.infra.event;

import com.example.demo.domain.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventService implements EventService {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public <T> void publish(T event) {
        eventPublisher.publishEvent(event);
    }
}
