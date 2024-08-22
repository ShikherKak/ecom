package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.controllerAdvice.ProductNotFoundException;
import com.ecom.productcatalogservice.dtos.ProductResponseDto;
import com.ecom.productcatalogservice.models.Category;
import com.ecom.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

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

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Product> getAllByCategory(Long categoryId) {
        return new ArrayList<>();
    }

    @Override
    public Page<Product> getAllByName(String Name, int startIndex, int pageSize) {
        return new Page<Product>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super Product, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Product> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Product> iterator() {
                return null;
            }
        };
    }
}
