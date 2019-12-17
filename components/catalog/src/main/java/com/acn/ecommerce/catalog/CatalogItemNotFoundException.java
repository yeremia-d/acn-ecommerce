package com.acn.ecommerce.catalog;

public class CatalogItemNotFoundException extends RuntimeException {

    public CatalogItemNotFoundException() {
        super("Catalog item not found");
    }

    public CatalogItemNotFoundException(Long id) {
        super(String.format("Catalog item with id %d not found or does not exist.", id));
    }
}
