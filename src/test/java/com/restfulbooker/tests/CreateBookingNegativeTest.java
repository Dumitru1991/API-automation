package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

class CreateBookingNegativeTest {

    @Test
    void createBookingWithEmptyBody() {

        given()
            .baseUri(ApiConfig.BASE_URL)
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/booking")
        .then()
            .log().all()
            .statusCode(500);
    }
}