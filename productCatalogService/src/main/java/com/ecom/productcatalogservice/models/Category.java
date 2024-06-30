package com.ecom.productcatalogservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Product> products;

}
