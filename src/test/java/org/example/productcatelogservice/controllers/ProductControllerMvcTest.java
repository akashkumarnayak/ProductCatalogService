package org.example.productcatelogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatelogservice.dto.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers=ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    ProductController controller;

    @MockBean
    @Qualifier("sps")
    IProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void Test_getAllProducts_SucessfullRun() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("MuscleBlaze Whey");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("MB Fish Oil");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);


        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("MuscleBlaze Whey");

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("MB Fish Oil");

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);
        productDtoList.add(productDto2);


        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                        .andExpect(status().isOk())
                        .andExpect(content().string(mapper.writeValueAsString(productDtoList)));
    }

    @Test
    public void Test_createProduct_SucessfullRun() throws Exception
    {
        //Arrange

        Product product = new Product();
        product.setId(1L);
        product.setName("MuscleBlaze Whey");

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("MuscleBlaze Whey");

        when(productService.createNewProduct(any(Product.class))).thenReturn(product);

        // Act and Assert

         mockMvc.perform(post("/products")
                         .content(mapper.writeValueAsString(productDto))
                         .contentType(MediaType.APPLICATION_JSON))
                         .andExpect(status().isOk())
                         .andExpect(content().string(mapper.writeValueAsString(productDto)))
         ;
    }
}
