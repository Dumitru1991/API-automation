
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class HealthCheckTest {

    @Test
    void healthCheckShouldReturn201() {
        given()
            .baseUri(ApiConfig.BASE_URL)
            .when()
            .get("/ping")
            .then()
            .statusCode(201);
    }
}
