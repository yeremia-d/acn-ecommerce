package com.acn.ecommerce.catalog.controllers;

import com.acn.ecommerce.catalog.models.CatalogItem;
import com.acn.ecommerce.catalog.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    // list
    @GetMapping
    List<CatalogItem> list(Pageable pageable) {
        return null;
    }

    // GetById
    @GetMapping("/{id}")
    CatalogItem getById(@PathVariable Long id) {
        return null;
    }

    // Get by category
    @GetMapping("/categoryId")
    List<CatalogItem> getByItemsByCategoryId(@PathVariable Long categoryId, Pageable pageable) {
        return null;
    }

    // create (when assigned a category, ensure all categories exist)
    @PostMapping
    CatalogItem create(@RequestBody CatalogItem catalogItem) {
        return null;
    }

    // update (when updating, ensure all categories exist)
    @PutMapping("/{id}")
    CatalogItem update(@RequestBody CatalogItem catalogItem) {
        return null;
    }

    //delete
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return null;
    }
}
