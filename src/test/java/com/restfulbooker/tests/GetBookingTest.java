
package com.restfulbooker.tests;

import com.restfulbooker.config.ApiConfig;
import org.junit.jupiter.api.Test;
import com.restfulbooker.testdata.BookingData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class GetBookingTest {

    @Test
    void shouldRetrieveBookingSuccessfully() {

        int bookingId =
            given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType("application/json")
                .body(BookingData.VALID_BOOKING)
            .when()
                .post("/booking")
            .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .extract()
                .path("bookingid");
                System.out.println("Created booking ID: " + bookingId);

        given()
    .baseUri(ApiConfig.BASE_URL)
    .pathParam("id", bookingId)
    .log().all()
    .when()
    .get("/booking/{id}")
    .then()
    .log().all()
    .statusCode(200)
    .body("firstname", equalTo("John"))
    .body("lastname", equalTo("Smith"))
    .body("totalprice", equalTo(150))
    .body("depositpaid", equalTo(true))
    .body("additionalneeds", equalTo("Breakfast"));
    }
}
