package com.example.demo.fixtures;

import com.example.demo.infra.web.request.CreateUserRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserTestFixtures {

    public static void bootstrapUser(List<String> usernames) {
        usernames.forEach(UserTestFixtures::createUser);
    }

    public static Response createUser(String username) {
        var request = new CreateUserRequest();
        request.setUsername(username);
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/users");
    }

    public static void deleteAllUsers() {
        when().delete("/test-fixtures/users").then().statusCode(204);
    }
}
