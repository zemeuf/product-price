package dev.decision.productprice;

import java.util.UUID;

public class Product {

  private UUID productId;

  private Double price;

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
