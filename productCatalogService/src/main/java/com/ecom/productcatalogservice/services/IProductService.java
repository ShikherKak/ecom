package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.models.Product;
import org.springframework.stereotype.Service;


public interface IProductService {

    public Product getProduct(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id,Product product);


}
