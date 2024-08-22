package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {

    public Product getProduct(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id,Product product);

    List<Product> getAllProducts();

    void deleteById(Long id);

    List<Product> getAllByCategory(Long categoryId);

    Page<Product> getAllByName(String Name, int startIndex, int pageSize);



}
