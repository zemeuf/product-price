package dev.decision.productprice.service;

import dev.decision.productprice.model.Product;
import dev.decision.productprice.exception.ProductNotFoundException;
import dev.decision.productprice.data.ProductRepository;
import dev.decision.productprice.model.ShoppingCartItem;
import java.util.UUID;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

  @Autowired
  ProductRepository productRepository;
  @Autowired
  private KieContainer kieContainer;

  public ShoppingCartItem getDiscount(UUID productId, Integer quantity) {
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
    Product product = productRepository.findProductByProductId(productId).orElseThrow(
        () -> new ProductNotFoundException("Product not found. ProductID: " + productId));
    shoppingCartItem.setProduct(product);
    shoppingCartItem.setQuantity(quantity);
    KieSession kieSession = kieContainer.newKieSession();
    kieSession.insert(shoppingCartItem);
    kieSession.fireAllRules();
    kieSession.dispose();
    shoppingCartItem.setFinalPrice(
        shoppingCartItem.getProduct().getPrice() * shoppingCartItem.getQuantity()
            - shoppingCartItem.getDiscount());
    return shoppingCartItem;
  }
}
