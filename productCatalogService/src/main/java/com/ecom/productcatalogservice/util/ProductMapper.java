package com.ecom.productcatalogservice.util;

import com.ecom.productcatalogservice.dtos.ProductRequestDto;
import com.ecom.productcatalogservice.models.Category;
import com.ecom.productcatalogservice.models.Product;

public class ProductMapper {

    public static Product createProductFromDTO(ProductRequestDto productRequestDto)
    {
        Product product = new Product();
        product.setName(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setImage(productRequestDto.getImage());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(productRequestDto.getCategory());
        return product;
    }

}
