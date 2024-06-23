package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.ProductCatalogServiceApplication;
import com.ecom.productcatalogservice.Repositories.ProductRepository;
import com.ecom.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProduct(Long id) {
        return new Product();
    }
}
