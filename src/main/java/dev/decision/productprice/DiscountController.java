package dev.decision.productprice;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @GetMapping("/get-discount")
  public ResponseEntity<ShoppingCartItem> getDiscount() {
    ShoppingCartItem discount = discountService.getDiscount(UUID.randomUUID(), 10);
    return new ResponseEntity<>(discount, HttpStatus.OK);
  }
}
