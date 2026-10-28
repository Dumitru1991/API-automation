# API Automation Framework

A beginner-friendly API test automation project built with Java, REST Assured, Maven, and JUnit 5.

## Technologies

- **Java 21** — programming language
- **REST Assured** — API testing library
- **Maven** — dependency management and build tool
- **JUnit 5** — test execution and assertions
- **Restful Booker API** — demo API used for testing

## Tests

The project currently includes:

- **Health Check:** verifies that the API is responding.
- **Create Booking:** creates a booking and validates the response.
- **Get Booking:** creates a booking, retrieves it by ID, and validates the returned data.

## Project Structure

```text
src/test/java/com/restfulbooker/
├── config/
│   └── ApiConfig.java
├── testdata/
│   └── BookingData.java
└── tests/
    ├── HealthCheckTest.java
    ├── CreateBookingTest.java
    └── GetBookingTest.java
```

## How to Run the Tests

Make sure Java 21 and Maven are installed.

Run all tests from the project root directory:

```bash
mvn test
```

## API Under Test

Base URL: https://restful-booker.herokuapp.com

This is a demo API intended for learning and practicing API testing.

## Future Improvements

- Add negative test cases.
- Add authentication tests.
- Improve test reporting.
- Integrate automated tests with GitHub Actions.
