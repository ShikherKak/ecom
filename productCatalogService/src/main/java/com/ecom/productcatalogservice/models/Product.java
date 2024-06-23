package com.ecom.productcatalogservice.models;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel{

    private String description;
    private Long price;
    private String Image;

    @ManyToOne
    private Category category;


}
