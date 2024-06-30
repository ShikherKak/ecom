package com.ecom.productcatalogservice.Repositories;

import com.ecom.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findById(Long id);

    Product save(Product product);

    List<Product> findAll();

    void deleteById(Long id);

    //this is the usage of JPQL
    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategory(@Param("categoryId")Long categoryId);

}
