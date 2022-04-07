package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class UserApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void should_retrieve_bootstrapped_users() {
        var userResources = when() // endpoint call
                .get("/api/users")
        .then() // response
                .statusCode(200)
                .extract()
                .body().jsonPath().getObject(".", new TypeRef<List<UserResource>>() {});

        assertThat(userResources).hasSize(3);
    }

    @Test
    void should_create_new_user() {
        var request = new CreateUserRequest();
        request.setUsername("zert");
        var location = given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .extract().header("Location");

        assertThat(location).isNotNull();

        var userResource = when()
                .get(location)
        .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", UserResource.class);

        assertThat(userResource.getName()).isEqualTo("zert");
        assertThat(userResource.getRequiredLink("self").getHref()).isNotNull();
    }

}