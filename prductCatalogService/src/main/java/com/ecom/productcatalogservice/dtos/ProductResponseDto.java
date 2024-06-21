package com.ecom.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    Long id;
    String title;
    Long price;
    String description;
    String image;
    String Category;

}
