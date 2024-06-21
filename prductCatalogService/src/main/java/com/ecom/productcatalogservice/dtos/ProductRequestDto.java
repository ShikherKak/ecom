package com.ecom.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    String title;
    Long price;
    String description;
    String image;
    String Category;

}
