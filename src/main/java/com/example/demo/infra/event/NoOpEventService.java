package com.example.demo.infra.event;

import com.example.demo.domain.event.EventService;

public class NoOpEventService implements EventService {
    @Override
    public <T> void publish(T event) {}
}
