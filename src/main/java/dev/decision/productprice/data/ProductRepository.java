package dev.decision.productprice.data;

import dev.decision.productprice.model.Product;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findProductByProductId(UUID productId);
}
