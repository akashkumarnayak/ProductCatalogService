package org.example.productcatelogservice.services;

import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("sps")
public class StorageProductService implements IProductService{

    @Autowired
    ProductRepo productRepo;

    public Product getProductById(Long id)
    {
        Optional<Product> product = productRepo.findById(id);
        return product.orElse(null);

    }

    public List<Product> getAllProducts()
    {
        return productRepo.findAll();
    }

    public Product replaceProductById(long id, Product product)
    {
        Optional<Product> productToBeReplaced = productRepo.findById(id);

        if(productToBeReplaced.isPresent())
        {
            productRepo.save(product);
            return product;
        }

        return null;
    }

    public Product createNewProduct(Product product)
    {
        Optional<Product> productToCreate = productRepo.findById(product.getId());

        if(productToCreate.isEmpty())
        {
            productRepo.save(product);
            return product;
        }

        return productToCreate.get();
    }

    public Product deleteProductById(long id)
    {
        Optional<Product> productToDelete = productRepo.findById(id);

        if(productToDelete.isPresent())
        {
            productRepo.delete(productToDelete.get());
            return productToDelete.get();
        }

        return null;
    }

}
