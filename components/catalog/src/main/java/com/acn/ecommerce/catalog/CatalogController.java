package com.acn.ecommerce.catalog;

import com.acn.ecommerce.catalog.data.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    Page<CatalogItem> list(Pageable pageable) {
        return catalogService.list(pageable);
    }

    @GetMapping("/{id}")
    CatalogItem getById(@PathVariable Long id) {
        return catalogService.getById(id);
    }

    @GetMapping("/categoryId")
    List<CatalogItem> getByItemsByCategoryId(@PathVariable Long categoryId, Pageable pageable) {
        return catalogService.listByCatalogId(categoryId, pageable);
    }

    // create (when assigned a category, ensure all categories exist)
    @PostMapping
    CatalogItem create(@RequestBody CatalogItem catalogItem) {
        return catalogService.create(catalogItem);
    }

    // update (when updating, ensure all categories exist)
    @PutMapping("/{id}")
    CatalogItem update(@PathVariable Long id, @RequestBody CatalogItem catalogItem) {
        return catalogService.update(id, catalogItem);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        catalogService.deleteById(id);
    }
}
