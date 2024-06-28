package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.ProductCatalogServiceApplication;
import com.ecom.productcatalogservice.Repositories.ProductRepository;
import com.ecom.productcatalogservice.controllerAdvice.ProductNotFoundException;
import com.ecom.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class ProductService implements IProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProduct(Long id) {
        Optional<Product> product= productRepository.findById(id);

        if(product.isEmpty())
        {
            throw new ProductNotFoundException("Product Not Found");
        }

        return product.get();
    }
}
