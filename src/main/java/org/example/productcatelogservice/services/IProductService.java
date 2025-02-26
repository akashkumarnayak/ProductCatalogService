package org.example.productcatelogservice.services;

import org.example.productcatelogservice.dto.ProductDto;
import org.example.productcatelogservice.models.Product;

import java.util.List;

public interface IProductService {

    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product replaceProductById(long id, Product product);
    public Product createNewProduct(Product product);
    public Product deleteProductById(long id);
}
