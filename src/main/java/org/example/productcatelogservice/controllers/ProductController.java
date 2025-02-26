package org.example.productcatelogservice.controllers;

import org.example.productcatelogservice.dto.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.services.FakeStoreProductService;
import org.example.productcatelogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService fakeStoreProductService;

    @GetMapping("/products")
    private List<ProductDto> getAllProducts()
    {
        List<Product> products = fakeStoreProductService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>() {};

        for(Product product : products)
        {
            productDtos.add(from(product));
        }

        return productDtos;
    }

    @GetMapping("/products/{id}")
    private ProductDto getProduct(@PathVariable Long id) {

        if (id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }
        Product product = fakeStoreProductService.getProductById(id);
        if(product==null)
        {
            return null;
        }

        return from(product);
    }

    @PostMapping("/products")
    private ProductDto createProduct(@RequestBody ProductDto productDto) {

        Product responseProduct = fakeStoreProductService.createNewProduct(from(productDto));
        return from(responseProduct);
    }

    @PatchMapping("/products/{id}")
    private ProductDto updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {

        if(id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }

        productDto.setName("i phone");
        return productDto;
    }

    @PutMapping("/products/{id}")
    private ProductDto replaceProduct (@PathVariable long id, @RequestBody ProductDto productDto) {

        if(id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }

        Product product = fakeStoreProductService.replaceProductById(id,from(productDto));
        return from(product);
    }

    @DeleteMapping("/products/{id}")
    private ProductDto deleteProduct(@PathVariable long id) {

        if(id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }

        Product product = fakeStoreProductService.deleteProductById(id);
        return from(product);
    }

    private ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setImageUrl(product.getImageUrl());

        return productDto;
    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setImageUrl(productDto.getImageUrl());
        //product.setId(productDto.getId());
        return product;
    }

}
