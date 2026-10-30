
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import com.restfulbooker.testdata.BookingData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class DeleteBookingTest {

    @Test
    void deleteBookingAndVerifyItNoLongerExists() {

        // Step 1: Create a booking.
        int bookingId =
            given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType(ContentType.JSON)
                .body(BookingData.VALID_BOOKING)
            .when()
                .post("/booking")
            .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        // Step 2: Obtain an authentication token.
        String token =
            given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "username": "admin",
                        "password": "password123"
                    }
                    """)
            .when()
                .post("/auth")
            .then()
                .statusCode(200)
                .extract()
                .path("token");

        // Step 3: Delete the booking.
        given()
            .baseUri(ApiConfig.BASE_URL)
            .cookie("token", token)
        .when()
            .delete("/booking/" + bookingId)
        .then()
            .statusCode(201);

        // Step 4: Verify the booking no longer exists.
        given()
            .baseUri(ApiConfig.BASE_URL)
        .when()
            .get("/booking/" + bookingId)
        .then()
            .statusCode(404);
    }
}
