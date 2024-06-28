package com.ecom.productcatalogservice.controllers;

import com.ecom.productcatalogservice.dtos.ProductRequestDto;
import com.ecom.productcatalogservice.models.Category;
import com.ecom.productcatalogservice.models.Product;
import com.ecom.productcatalogservice.services.IProductService;
import com.ecom.productcatalogservice.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    IProductService productService;

    @Autowired
    public ProductController(IProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/product/getAllProducts")
    public List<Product> getProduct()
    {
        return new ArrayList<>();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId)
    {
        return productService.getProduct(productId);
    }

    @PostMapping("/product/addProduct")
    public Product addNewProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        Product product = ProductMapper.createProductFromDTO(productRequestDto);
        return productService.addProduct(product);
    }

    @PutMapping("/product/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto)
    {
        Product product = ProductMapper.createProductFromDTO(productRequestDto);
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/product/delete/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id)
    {
        return true;
    }


}
