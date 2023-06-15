package dev.decision.productprice;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductPriceApplicationTests {

  @LocalServerPort
  private Integer port;

  @BeforeEach
  public void setup() {
    RestAssured.port = port;
  }

  @Test
  void givenNotExistingProduct_whenSearching_thenStatus404() {
    when()
        .get("/products/f94f8296-2b64-4220-aca0-0e2af89a4e00/final-price?amount=10")
        .then()
        .statusCode(404)
        .body("message", is("Product not found. ProductID: f94f8296-2b64-4220-aca0-0e2af89a4e00"));
  }

  @Test
  void givenExistingProduct_whenSearching_thenStatusOK() {
    when()
        .get("/products/68b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price?amount=10")
        .then()
        .statusCode(200);
  }

  @Test
  void givenExistingProduct_whenSearching_thenMatchFinalPrice() {
    when()
        .get("/products/68b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price?amount=10")
        .then()
        .body("finalPrice", is(2990.0F));
  }

  @Test
  void givenExistingProduct_whenSearchingWithNoAmount_thenStatus400() {
    when()
        .get("/products/68b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price")
        .then()
        .statusCode(400)
        .body("error", is("Bad Request"));
  }

  @Test
  void givenExistingProduct_whenSearchingWithAmount0_thenValidationError() {
    when()
        .get("/products/68b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price?amount=0")
        .then()
        .statusCode(500)
        .body("message", containsString("amount: must be greater than or equal to 1"));
  }
}
