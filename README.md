# API Automation Framework

A REST API automation testing project built with Java 21, REST Assured, Maven, and JUnit 5.

The project uses the [Restful Booker API](https://restful-booker.herokuapp.com/) as a demo API to practice automated API testing.

## Technologies

- Java 21
- REST Assured 5.5.0
- JUnit 5.10.2
- Maven
- VS Code

## Project Structure

```text
src
└── test
    └── java
        └── com
            └── restfulbooker
                ├── config
                │   └── ApiConfig.java
                ├── testdata
                │   └── BookingData.java
                └── tests
                    ├── HealthCheckTest.java
                    ├── CreateBookingTest.java
                    ├── GetBookingTest.java
                    ├── CreateBookingNegativeTest.java
                    ├── GetBookingListTest.java
                    ├── UpdateBookingTest.java
                    ├── PartialUpdateBookingTest.java
                    ├── DeleteBookingTest.java
                    ├── AuthenticationNegativeTest.java
                    └── GetNonExistingBookingTest.java
```

## Test Coverage

The framework covers:

- API health check
- Creating bookings
- Retrieving bookings by ID
- Retrieving and filtering booking lists
- Full booking updates using PUT
- Partial booking updates using PATCH
- Deleting bookings
- Authentication with invalid credentials
- Negative scenarios, including empty requests and non-existent booking IDs

## Prerequisites

- Java JDK 21
- Maven
- Git

Verify your installation:

```bash
java -version
mvn -version
```

## Running the Tests

Clone the repository:

```bash
git clone https://github.com/Dumitru1991/API-automation.git
cd API-automation
```

Run all tests:

```bash
mvn clean test
```

Run an individual test class:

```bash
mvn -Dtest=CreateBookingTest test
```

## Test Results

The full test suite has been executed successfully:

- 11 tests
- 0 failures
- 0 errors
- 0 skipped

## Project Purpose

This project was created to develop practical skills in REST API automation testing, including HTTP methods, request and response validation, authentication, negative testing, and Maven-based test execution.