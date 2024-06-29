package com.ecom.productcatalogservice.services;

import com.ecom.productcatalogservice.ProductCatalogServiceApplication;
import com.ecom.productcatalogservice.Repositories.CategoryRepository;
import com.ecom.productcatalogservice.Repositories.ProductRepository;
import com.ecom.productcatalogservice.controllerAdvice.ProductNotFoundException;
import com.ecom.productcatalogservice.models.Category;
import com.ecom.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductService implements IProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isEmpty())
        {
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            Category categoryToSave = categoryRepository.save(newCategory);
            product.setCategory(categoryToSave);
        }
        else
        {
            product.setCategory(categoryOptional.get());
        }

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if(existingProduct.isEmpty())
        {
            throw new ProductNotFoundException("Product Not Found");
        }

        Product oldProduct = existingProduct.get();
        Product updatedProduct = new Product();

        updatedProduct.setDescription(product.getDescription() != null ? product.getDescription() : oldProduct.getDescription());
        updatedProduct.setName(product.getName() != null ? product.getName() : oldProduct.getName());
        updatedProduct.setPrice(product.getPrice() != null ? product.getPrice() : oldProduct.getPrice());
        updatedProduct.setImage(product.getImage() != null ? product.getImage() : oldProduct.getImage());
        updatedProduct.setId(id);

        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isEmpty())
        {
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            Category categoryToSave = categoryRepository.save(newCategory);
            updatedProduct.setCategory(categoryToSave);
        }
        else
        {
            updatedProduct.setCategory(categoryOptional.get());
        }
        return productRepository.save(updatedProduct);
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> allproducts = productRepository.findAll();

        if(allproducts.isEmpty())
        {
            throw new ProductNotFoundException("No Products found");
        }

        return allproducts;

    }

    @Override
    public void deleteById(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty())
        {
            throw new ProductNotFoundException("Product Not Found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllByCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId);
    }

}
