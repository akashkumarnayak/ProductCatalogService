package org.example.productcatelogservice.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends BaseModel {

    private String name;
    private String description;
    private String imageUrl;
    private boolean isPrime;
    private ProductCategory category;
    private Long price;
    private long id;

}
