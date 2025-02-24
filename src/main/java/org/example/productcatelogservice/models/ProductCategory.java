package org.example.productcatelogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ProductCategory extends BaseModel {

    private String name;
    private String description;
    private List<Product> products;
}
