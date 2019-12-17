package com.acn.ecommerce.catalog.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(Long id) {
        super(String.format("Category with id %d not found or does not exist.", id));
    }

}
