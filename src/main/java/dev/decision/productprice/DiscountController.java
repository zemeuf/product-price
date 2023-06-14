package dev.decision.productprice;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @GetMapping("/products/{productId}/final-price")
  public ResponseEntity<ShoppingCartItem> calculateFinalPrice(@PathVariable("productId") String productId, @RequestParam Integer amount) {
    ShoppingCartItem discount = discountService.getDiscount(UUID.fromString(productId), amount);
    return new ResponseEntity<>(discount, HttpStatus.OK);
  }
}
