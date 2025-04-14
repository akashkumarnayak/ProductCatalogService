package org.example.productcatelogservice.repos;

import org.example.productcatelogservice.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    Optional<Product> findById(Long id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(Long id);

    List<Product> findProductByOrderByPriceAsc();

    @Query("select p.name from Product p where p.id=:id")
    String findProductNameById(Long id);

    @Query("select pc.name from Product p join ProductCategory pc on p.category.id=pc.id")
    String findCategoryNameFromProductId(Long id);
}
