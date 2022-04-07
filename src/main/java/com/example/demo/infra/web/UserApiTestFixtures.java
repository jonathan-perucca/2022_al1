package com.example.demo.infra.web;

import com.example.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("!prod")
@RestController
@RequestMapping("/test-fixtures/users")
@RequiredArgsConstructor
public class UserApiTestFixtures {

    private final UserRepository userRepository;

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        userRepository.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
