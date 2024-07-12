package gift.repository;

import gift.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryInterface extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
