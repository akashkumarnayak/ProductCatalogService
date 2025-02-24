package org.example.productcatelogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private long price;

}
