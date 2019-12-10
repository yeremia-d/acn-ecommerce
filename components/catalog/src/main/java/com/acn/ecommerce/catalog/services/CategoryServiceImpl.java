package com.acn.ecommerce.catalog.services;

import com.acn.ecommerce.catalog.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {


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
