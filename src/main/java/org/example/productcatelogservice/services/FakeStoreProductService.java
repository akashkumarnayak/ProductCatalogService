package org.example.productcatelogservice.services;

import org.example.productcatelogservice.dto.FakeStoreProductDto;
import org.example.productcatelogservice.dto.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Product replaceProductById(long id, Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = from(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, fakeStoreProductDto, FakeStoreProductDto.class,id);

        if(fakeStoreProductDtoResponseEntity.getBody()!=null && fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)))
        {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    public Product getProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,id);

        if(fakeStoreProductDto.getBody()!=null && fakeStoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200)))
        {
            return from(fakeStoreProductDto.getBody());
        }

        return null;
    }

    public Product createNewProduct(Product product) {

        ResponseEntity<FakeStoreProductDto> responseEntity = requestForEntity("https://fakestoreapi.com/products", HttpMethod.POST,from(product), FakeStoreProductDto.class);

        if(responseEntity.getBody()!=null && responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)))
        {
            return from(responseEntity.getBody());
        }

        return null;
    }

    public List<Product> getAllProducts()
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<Product> products = new ArrayList<>();

        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class );

        if(fakeStoreProductDto.getBody() !=null && fakeStoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200)))
        {
            for(FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDto.getBody())
            {
                products.add(from(fakeStoreProductDto1));
            }
        }

        return products;
    }

    public <T> ResponseEntity<T> requestForEntity(String url,HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    Product from(FakeStoreProductDto fakeStoreProductDto)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        ProductCategory category = new ProductCategory();
        category.setName(fakeStoreProductDto.getCategory());
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    FakeStoreProductDto from(Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }
}
