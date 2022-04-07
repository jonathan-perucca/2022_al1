package com.example.demo;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class RequestCounter implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestCounter.class);
    private final Map<Endpoint, AtomicLong> counters;

    public RequestCounter(Map<Endpoint, AtomicLong> counters) {
        this.counters = counters;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var endpoint = new Endpoint(request);
        var newCounterValue = counters.computeIfAbsent(endpoint, (e) -> new AtomicLong(0)).incrementAndGet();

        log.info("{} has been called {} times", endpoint, newCounterValue);

        return true;
    }
}

@Data
class Endpoint {
    private String method;
    private String path;

    public Endpoint(HttpServletRequest request) {
        this.method = request.getMethod();
        this.path = request.getRequestURI();
    }
}