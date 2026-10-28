
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import com.restfulbooker.testdata.BookingData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

class GetBookingListTest {

    @Test
    void getAllBookingIds() {

        given()
            .baseUri(ApiConfig.BASE_URL)
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("bookingid", everyItem(notNullValue()));
    }

    @Test
    void filterBookingsByFirstAndLastName() {

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

        given()
            .baseUri(ApiConfig.BASE_URL)
            .queryParam("firstname", "John")
            .queryParam("lastname", "Smith")
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("bookingid", hasItem(bookingId));
    }
}
