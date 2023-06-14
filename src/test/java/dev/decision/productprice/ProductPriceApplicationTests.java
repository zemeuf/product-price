package dev.decision.productprice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

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
		when().get("/products/48b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price?amount=10").then().statusCode(404);
	}
	@Test
	void givenExistingProduct_whenSearching_thenStatusOK() {
		when().get("/products/68b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price?amount=10").then().statusCode(200);
	}

	@Test
	void givenExistingProduct_whenSearching_thenMatchFinalPrice() {
		when().get("/products/68b0a517-ebee-4e75-a514-ccba8f4af4ba/final-price?amount=10").then().body("finalPrice", is(2990.0F));
	}
}
