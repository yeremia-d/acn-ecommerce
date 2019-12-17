package com.acn.ecommerce.catalog.services;

import com.acn.ecommerce.catalog.exceptions.CategoryNotFoundException;
import com.acn.ecommerce.catalog.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> list();

    Category getById(Long id) throws CategoryNotFoundException;

    Category create(Category category);

    Category update(Long id, Category category) throws CategoryNotFoundException;

    Long deleteById(Long id) throws CategoryNotFoundException;
}
