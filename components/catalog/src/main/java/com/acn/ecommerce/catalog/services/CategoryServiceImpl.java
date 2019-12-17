package com.acn.ecommerce.catalog.services;

import com.acn.ecommerce.catalog.exceptions.CategoryNotFoundException;
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
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) throws CategoryNotFoundException {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) throws CategoryNotFoundException {
        return categoryRepository
                .findById(id)
                .map(existingCategory -> {
                    Category updated = Category.builder()
                            .id(existingCategory.getId())
                            .name(category.getName())
                            .description(category.getDescription())
                            .items(existingCategory.getItems())
                            .build();
                    return categoryRepository.save(updated);
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Long deleteById(Long id) throws CategoryNotFoundException {
        return categoryRepository
                .findById(id)
                .map(categoryToDelete -> {
                    categoryRepository.deleteById(categoryToDelete.getId());
                    return categoryToDelete.getId();
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
