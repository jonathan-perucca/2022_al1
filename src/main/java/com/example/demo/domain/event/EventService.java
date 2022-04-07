package com.example.demo.domain.event;

public interface EventService {

    <T> void publish(T event);
}
