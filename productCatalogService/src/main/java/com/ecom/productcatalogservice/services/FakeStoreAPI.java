package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.controllerAdvice.ProductNotFoundException;
import com.ecom.productcatalogservice.dtos.ProductResponseDto;
import com.ecom.productcatalogservice.models.Category;
import com.ecom.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreAPI implements IProductService{


    private final RestTemplate restTemplate;

    public FakeStoreAPI(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    private Product getProductFromFakeStore(ProductResponseDto productResponseDto)
    {
        Product product = new Product();
        product.setId(productResponseDto.getId());
        product.setName(productResponseDto.getTitle());
        product.setPrice(productResponseDto.getPrice());
        product.setImage(productResponseDto.getImage());
        product.setDescription(productResponseDto.getImage());
        Category category = new Category();
        category.setName(productResponseDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProduct(Long id) {
        ProductResponseDto productResponseDto;
        try{
            productResponseDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id.toString(), ProductResponseDto.class);
        }catch(Exception ex)
        {
            throw new ProductNotFoundException("Product Not Found");
        }
        //need to add Exception handling
        return getProductFromFakeStore(productResponseDto);
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id,Product product) {
        return null;
    }
}
