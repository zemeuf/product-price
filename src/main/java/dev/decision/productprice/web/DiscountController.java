package dev.decision.productprice.web;

import dev.decision.productprice.service.DiscountService;
import dev.decision.productprice.exception.ProductNotFoundException;
import dev.decision.productprice.model.ShoppingCartItem;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @GetMapping("/products/{productId}/final-price")
  public ResponseEntity<ShoppingCartItem> calculateFinalPrice(
      @PathVariable("productId") String productId, @RequestParam @Min(1) Integer amount) {
    ShoppingCartItem discount = discountService.getDiscount(UUID.fromString(productId), amount);
    return new ResponseEntity<>(discount, HttpStatus.OK);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ApiError> handleProductNotFoundException(
      ProductNotFoundException exception) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleAmountParamException(
      ConstraintViolationException exception) {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}
