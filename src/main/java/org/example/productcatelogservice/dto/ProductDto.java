package org.example.productcatelogservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatelogservice.models.ProductCategory;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Long price;
    private ProductCategory category;

}
