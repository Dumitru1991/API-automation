
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import com.restfulbooker.testdata.BookingData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UpdateBookingTest {

    @Test
    void updateBookingAndVerifyChanges() {

        // Step 1: Create a booking and capture its ID.
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

        assertNotNull(bookingId);

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

        assertNotNull(token);

        // Step 3: Replace the booking details.
        String updatedBooking = """
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 250,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2026-12-01",
                    "checkout": "2026-12-07"
                },
                "additionalneeds": "Lunch"
            }
            """;

        given()
            .baseUri(ApiConfig.BASE_URL)
            .contentType(ContentType.JSON)
            .cookie("token", token)
            .body(updatedBooking)
        .when()
            .put("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Jane"))
            .body("lastname", equalTo("Doe"))
            .body("totalprice", equalTo(250))
            .body("depositpaid", equalTo(false))
            .body("bookingdates.checkin", equalTo("2026-12-01"))
            .body("bookingdates.checkout", equalTo("2026-12-07"))
            .body("additionalneeds", equalTo("Lunch"));

        // Step 4: Retrieve the booking and verify persistence.
        given()
            .baseUri(ApiConfig.BASE_URL)
        .when()
            .get("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Jane"))
            .body("lastname", equalTo("Doe"))
            .body("totalprice", equalTo(250))
            .body("depositpaid", equalTo(false))
            .body("additionalneeds", equalTo("Lunch"));
    }
}
