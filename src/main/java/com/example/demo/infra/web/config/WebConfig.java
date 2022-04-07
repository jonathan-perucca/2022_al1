package com.example.demo.infra.web.config;

import com.example.demo.infra.web.interceptor.RequestCounter;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HTTP_PROBLEM_DETAILS;

@Configuration
@EnableHypermediaSupport(type = HTTP_PROBLEM_DETAILS)
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestCounter(new ConcurrentHashMap<>()));
    }
}

