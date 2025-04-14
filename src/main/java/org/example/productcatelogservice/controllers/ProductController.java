package org.example.productcatelogservice.controllers;

import org.example.productcatelogservice.dto.ProductCategoryDto;
import org.example.productcatelogservice.dto.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.models.ProductCategory;
import org.example.productcatelogservice.services.FakeStoreProductService;
import org.example.productcatelogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
            @Qualifier("sps")
    IProductService productService;

    @GetMapping()
    private List<ProductDto> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>() {};

        for(Product product : products)
        {
            productDtos.add(from(product));
        }

        return productDtos;
    }

    @GetMapping("/{id}")
    ProductDto getProduct(@PathVariable Long id) {

        if (id<0)
        {
            throw new IllegalArgumentException("Product id must be positive integer");
        }
        else if(id==0)
        {
            throw new IllegalArgumentException("Product id must be greater than zero");
        }


        Product product = productService.getProductById(id);
        if(product==null)
        {
            return null;
        }

        return from(product);
    }

    @PostMapping()
    private ProductDto createProduct(@RequestBody ProductDto productDto) {

        Product responseProduct = productService.createNewProduct(from(productDto));
        return from(responseProduct);
    }

    @PatchMapping("/{id}")
    private ProductDto updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {

        if(id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }

        productDto.setName("i phone");
        return productDto;
    }

    @PutMapping("/{id}")
    private ProductDto replaceProduct (@PathVariable long id, @RequestBody ProductDto productDto) {

        if(id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }

        Product product = productService.replaceProductById(id,from(productDto));
        return from(product);
    }

    @DeleteMapping("/{id}")
    private ProductDto deleteProduct(@PathVariable long id) {

        if(id<=0)
        {
            throw new IllegalArgumentException("Product id must be a positive integer");
        }

        Product product = productService.deleteProductById(id);
        return from(product);
    }

    private ProductDto from(Product product){

        if(product==null)
        {
            return null;
        }

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());

        if(product.getCategory()!=null) {
            ProductCategoryDto productCategory = new ProductCategoryDto();
            productCategory.setId(product.getId());
            productCategory.setName(product.getCategory().getName());
            productCategory.setDescription(product.getCategory().getDescription());
            productDto.setCategory(productCategory);
        }

        productDto.setImageUrl(product.getImageUrl());

        return productDto;
    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());

        if(productDto.getCategory()!=null) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(productDto.getCategory().getId());
            productCategory.setName(productDto.getCategory().getName());
            productCategory.setDescription(productDto.getCategory().getDescription());
            product.setCategory(productCategory);
        }

        return product;
    }

}
