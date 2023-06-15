package dev.decision.productprice.model;

public class ShoppingCartItem {

  private Product product;
  private int quantity;
  private Double discount;
  private Double finalPrice;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(Double finalPrice) {
    this.finalPrice = finalPrice;
  }
}
