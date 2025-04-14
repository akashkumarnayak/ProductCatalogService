package org.example.productcatelogservice.repos;

import jdk.jfr.Category;
import org.example.productcatelogservice.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findById(Long id);
}
