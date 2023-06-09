package dev.decision.productprice;

import java.util.UUID;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
  @Autowired
  private KieContainer kieContainer;

  @Autowired ProductRepository productRepository;

  public ShoppingCartItem getDiscount(UUID productId, Integer quantity) {
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
    Product product = productRepository.findProductByProductId(productId);
    shoppingCartItem.setProduct(product);
    shoppingCartItem.setQuantity(quantity);
    KieSession kieSession = kieContainer.newKieSession();
    kieSession.insert(shoppingCartItem);
    kieSession.fireAllRules();
    kieSession.dispose();
    return shoppingCartItem;
  }
}
