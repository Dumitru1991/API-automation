
package com.restfulbooker.testdata;

public final class BookingData {

    private BookingData() {
        // Prevent creating objects from this utility class.
    }

    public static final String VALID_BOOKING = """
            {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 150,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2026-11-01",
                    "checkout": "2026-11-05"
                },
                "additionalneeds": "Breakfast"
            }
            """;
}
