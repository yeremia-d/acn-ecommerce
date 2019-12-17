package com.acn.ecommerce.category;

import com.acn.ecommerce.category.data.Category;

import java.util.List;

public interface CategoryService {

    List<Category> list();

    Category getById(Long id) throws CategoryNotFoundException;

    Category create(Category category);

    Category update(Long id, Category category) throws CategoryNotFoundException;

    Long deleteById(Long id) throws CategoryNotFoundException;
}
