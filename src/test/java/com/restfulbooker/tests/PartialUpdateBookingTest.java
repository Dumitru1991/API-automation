
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import com.restfulbooker.testdata.BookingData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PartialUpdateBookingTest {

    @Test
    void updateFirstNameAndPreserveOtherFields() {

        // Create a booking and capture its ID.
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

        // Obtain an authentication token.
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

        // Change only the first name using PATCH.
        given()
            .baseUri(ApiConfig.BASE_URL)
            .contentType(ContentType.JSON)
            .cookie("token", token)
            .body("""
                {
                    "firstname": "Michael"
                }
                """)
        .when()
            .patch("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Michael"));

        // Retrieve the booking and verify unchanged fields.
        given()
            .baseUri(ApiConfig.BASE_URL)
        .when()
            .get("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Michael"))
            .body("lastname", equalTo("Smith"))
            .body("totalprice", equalTo(150))
            .body("depositpaid", equalTo(true))
            .body("additionalneeds", equalTo("Breakfast"));
    }
}
