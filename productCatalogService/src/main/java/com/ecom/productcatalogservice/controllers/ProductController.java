package com.ecom.productcatalogservice.controllers;

import com.ecom.productcatalogservice.dtos.ProductRequestDto;
import com.ecom.productcatalogservice.models.Product;
import com.ecom.productcatalogservice.services.IProductService;
import com.ecom.productcatalogservice.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return productService.getAllProducts();
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
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteById(id);
        return ResponseEntity.noContent().build(); //returns a no body 204 status
    }

    @GetMapping("/product/getAllByCategory/{categoryId}")
    public List<Product> getAllByCategory(@PathVariable("categoryId") Long categoryId)
    {
        return productService.getAllByCategory(categoryId);
    }



}
