package com.ecom.productcatalogservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String name;
    private String description;
    private Long price;
    private String Image;
    private Category category;


}
