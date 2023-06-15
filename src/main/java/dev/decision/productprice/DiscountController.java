package dev.decision.productprice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @GetMapping("/products/{productId}/final-price")
  public ResponseEntity<ShoppingCartItem> calculateFinalPrice(
      @PathVariable("productId") String productId, @RequestParam Integer amount) {
    ShoppingCartItem discount = discountService.getDiscount(UUID.fromString(productId), amount);
    return new ResponseEntity<>(discount, HttpStatus.OK);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ApiError> handleProductNotFoundException(
      ProductNotFoundException exception) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}
