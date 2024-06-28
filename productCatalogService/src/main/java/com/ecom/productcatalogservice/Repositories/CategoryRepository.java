package com.ecom.productcatalogservice.Repositories;

import com.ecom.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);

    Optional<Category> findByName(String name);

}
