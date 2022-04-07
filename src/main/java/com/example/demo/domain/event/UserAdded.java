package com.example.demo.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
public class UserAdded {
    private String name;
}