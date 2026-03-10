package com.apitest.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected static RequestSpecification requestSpec;
    private static PrintStream logStream;

    @BeforeClass
    public void setupBase() {
        log.info("========== TEST SUITE STARTED ==========");
        log.info("Base URI: https://petstore.swagger.io/v2");

        try {
            logStream = new PrintStream(
                    new FileOutputStream("src/test/resources/logs/restassured.log", true)
            );
        } catch (Exception e) {
            log.error("Could not create log file: " + e.getMessage());
        }

        // Reusable Request Specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter(logStream))   // logs all requests to file
                .addFilter(new ResponseLoggingFilter(logStream))  // logs all responses to file
                .build();

        log.info("Base setup complete — request spec built");
    }

    @AfterClass
    public void teardown() {
        log.info("========== TEST SUITE FINISHED ==========");
        if (logStream != null) logStream.close();
    }
}
