package org.example.productcatelogservice.controllers;

import org.example.productcatelogservice.controllers.ProductController;
import org.example.productcatelogservice.dto.ProductDto;
import org.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {

    @MockBean
    @Qualifier("sps")
    private IProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    public void TestGetProduct_WithNegativeProductId_ResultsInIllegalArgumentException()
    {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,()->productController.getProduct(-1L));

        assertEquals(exception.getMessage(),"Product id must be positive integer");

    }
}