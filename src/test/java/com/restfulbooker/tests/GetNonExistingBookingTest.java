package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GetNonExistingBookingTest {

    @Test
    void getNonExistingBookingShouldReturn404() {

        given()
            .baseUri(ApiConfig.BASE_URL)
        .when()
            .get("/booking/999999999")
        .then()
            .statusCode(404);
    }
}