
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import org.junit.jupiter.api.Test;
import com.restfulbooker.testdata.BookingData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class CreateBookingTest {

    @Test
    void shouldCreateBookingSuccessfully() {

        given()
            .baseUri(ApiConfig.BASE_URL)
            .contentType("application/json")
            .body(BookingData.VALID_BOOKING)
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .body("bookingid", notNullValue())
            .body("booking.firstname", equalTo("John"))
            .body("booking.lastname", equalTo("Smith"))
            .body("booking.totalprice", equalTo(150))
            .body("booking.depositpaid", equalTo(true))
            .body("booking.additionalneeds", equalTo("Breakfast"));
    }
}
