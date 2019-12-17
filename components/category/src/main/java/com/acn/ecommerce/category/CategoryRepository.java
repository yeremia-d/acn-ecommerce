package com.acn.ecommerce.category;

import com.acn.ecommerce.category.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}