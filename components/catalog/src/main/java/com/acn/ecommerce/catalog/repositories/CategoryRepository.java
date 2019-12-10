package com.acn.ecommerce.catalog.repositories;

import com.acn.ecommerce.catalog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}