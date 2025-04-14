package org.example.productcatelogservice.repos;

import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.models.ProductCategory;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryRepoTest {

    @Autowired
    ProductCategoryRepo productCategoryRepo;

    @Test
    @Transactional
    void testQueries()
    {
        ProductCategory productCategory =  productCategoryRepo.findById(2L).get();

        System.out.println(productCategory.getName());

//        for(Product product : productCategory.getProducts())
//        {
//            System.out.println(product.getName());
//        }

          for(ProductCategory productCategory2 : productCategoryRepo.findAll())
          {
              for(Product product : productCategory2.getProducts())
              {
                  System.out.println(product.getName());
              }
          }
    }
}