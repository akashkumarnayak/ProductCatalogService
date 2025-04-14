package org.example.productcatelogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatelogservice.models.ProductCategory;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Long price;
    private ProductCategoryDto category;

}
