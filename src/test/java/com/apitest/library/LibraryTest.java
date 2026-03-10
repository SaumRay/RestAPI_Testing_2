package com.apitest.library;

import com.apitest.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LibraryTest extends BaseTest {

    private static long addedBookId;  //  For chaining POST → GET → DELETE

    @Test(priority = 1)
    public void testGetAllAvailableBooks() {
        log.info("TEST 1: Fetching all available books in library");

        given()
                .spec(requestSpec)
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue())
                .body("[0].status", equalTo("available"));

        log.info("All available books fetched successfully");
    }

    @Test(priority = 2)
    public void testAddNewBookToLibrary() {
        log.info("TEST 2: Adding new book to library");

        String newBook = """
                {
                    "id": 998877,
                    "category": {
                        "id": 1,
                        "name": "Fiction"
                    },
                    "name": "The Alchemist",
                    "photoUrls": ["https://example.com/alchemist.jpg"],
                    "tags": [
                        {
                            "id": 1,
                            "name": "Paulo Coelho"
                        }
                    ],
                    "status": "available"
                }
                """;

        Response response =
                given()
                        .spec(requestSpec)
                        .body(newBook)
                        .when()
                        .post("/pet")
                        .then()
                        .statusCode(200)
                        .body("id", equalTo(998877))
                        .body("name", equalTo("The Alchemist"))
                        .body("status", equalTo("available"))
                        .extract().response();

        // 🔗 Capture ID for chaining
        addedBookId = response.jsonPath().getLong("id");
        log.info("Book added with ID: " + addedBookId);
    }

    @Test(priority = 3, dependsOnMethods = "testAddNewBookToLibrary")
    public void testGetBookById() {
        log.info("TEST 3: Fetching book by chained ID: " + addedBookId);

        given()
                .spec(requestSpec)
                .when()
                .get("/pet/" + addedBookId)
                .then()
                .statusCode(200)
                .body("id", equalTo((int) addedBookId))
                .body("name", equalTo("The Alchemist"))
                .body("category.name", equalTo("Fiction"));

        log.info("Book fetched successfully using chained ID");
    }

    @Test(priority = 4, dependsOnMethods = "testAddNewBookToLibrary")
    public void testBorrowBook() {
        log.info("TEST 4: Borrowing book — updating status to pending");

        String borrowedBook = """
                {
                    "id": 998877,
                    "category": {
                        "id": 1,
                        "name": "Fiction"
                    },
                    "name": "The Alchemist",
                    "photoUrls": ["https://example.com/alchemist.jpg"],
                    "tags": [
                        {
                            "id": 1,
                            "name": "Paulo Coelho"
                        }
                    ],
                    "status": "pending"
                }
                """;

        given()
                .spec(requestSpec)
                .body(borrowedBook)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("status", equalTo("pending"))
                .body("name", equalTo("The Alchemist"));

        log.info("Book status updated to 'pending' (borrowed)");
    }

    @Test(priority = 5, dependsOnMethods = "testAddNewBookToLibrary")
    public void testReturnBook() {
        log.info("TEST 5: Returning book — updating status to available");

        given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")  // override content type
                .formParam("name", "The Alchemist")
                .formParam("status", "available")
                .when()
                .post("/pet/" + addedBookId)
                .then()
                .statusCode(200)
                .body("message", notNullValue());

        log.info("Book returned — status back to available");
    }

    @Test(priority = 6, dependsOnMethods = "testAddNewBookToLibrary")
    public void testDeleteBook() {
        log.info("TEST 6: Deleting book from library ID: " + addedBookId);

        given()
                .spec(requestSpec)
                .when()
                .delete("/pet/" + addedBookId)
                .then()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(addedBookId)));

        log.info("Book deleted from library");
    }

    @Test(priority = 7, dependsOnMethods = "testDeleteBook")
    public void testVerifyBookDeleted() {
        log.info("TEST 7: Verifying deleted book returns 404");

        given()
                .spec(requestSpec)
                .when()
                .get("/pet/" + addedBookId)
                .then()
                .statusCode(404);

        log.info("Confirmed — book no longer exists (404)");
    }

    @Test(priority = 8)
    public void testGetBookInvalidId() {
        log.info("TEST 8: Negative test — fetching book with invalid ID");

        given()
                .spec(requestSpec)
                .when()
                .get("/pet/abc123")   // invalid non-numeric ID
                .then()
                .statusCode(404);

        log.info("404 returned for invalid book ID");
    }
}
