package com.ecom.productcatalogservice.models;

import jakarta.persistence.CascadeType;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;


}
