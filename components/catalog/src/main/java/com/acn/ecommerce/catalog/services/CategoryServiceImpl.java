package com.acn.ecommerce.catalog.services;

import com.acn.ecommerce.catalog.models.Category;
import com.acn.ecommerce.catalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> list() {
        return null;
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
