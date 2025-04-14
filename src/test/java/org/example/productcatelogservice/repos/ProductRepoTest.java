package org.example.productcatelogservice.repos;

import jakarta.transaction.Transactional;
import org.example.productcatelogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testQueries()
    {
//        List<Product> products = productRepo.findProductByOrderByPriceAsc();
//        for(Product product : products)
//        {
//            System.out.println(product.getName());
//        }

        System.out.println(productRepo.findProductNameById(2L));
    }
}