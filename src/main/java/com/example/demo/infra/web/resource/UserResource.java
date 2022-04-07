package com.example.demo.infra.web.resource;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@Accessors(chain = true)
public class UserResource extends RepresentationModel<UserResource> {
    private String name;
}