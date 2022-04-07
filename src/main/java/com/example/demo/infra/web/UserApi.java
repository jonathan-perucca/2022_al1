package com.example.demo.infra.web;

import com.example.demo.domain.User;
import com.example.demo.domain.UserService;
import com.example.demo.infra.web.request.CreateUserRequest;
import com.example.demo.infra.web.resource.UserResource;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    // equivalent to @RequiredArgsConstructor
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getUsers() {
        var userResources = userService.getUsers().stream()
                .map(this::toResource)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResources);
    }

    // /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable String id) {
        return userService.getUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(this::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        var newUser = userService.add(request.getUsername());

        return ResponseEntity.created(linkTo(
                methodOn(UserApi.class).getUser(newUser.getId())
        ).toUri()).build();
    }

    private UserResource toResource(User user) {
        var userResource = new UserResource()
                .setName(user.getUsername());
        userResource.add(linkTo(
                methodOn(UserApi.class).getUser(user.getId())
        ).withSelfRel());

        return userResource;
    }
}