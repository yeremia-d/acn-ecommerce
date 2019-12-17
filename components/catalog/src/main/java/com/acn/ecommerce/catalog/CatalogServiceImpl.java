package com.acn.ecommerce.catalog;

import com.acn.ecommerce.catalog.data.CatalogItem;
import com.acn.ecommerce.category.CategoryNotFoundException;
import com.acn.ecommerce.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    CatalogRepository catalogRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository, CategoryRepository categoryRepository) {
        this.catalogRepository = catalogRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CatalogItem> list(Pageable pageable) {
        return catalogRepository.findAll(pageable);
    }

    @Override
    public List<CatalogItem> listByCatalogId(Long categoryId, Pageable pageable) {

        return categoryRepository
                .findById(categoryId)
                .map(category -> catalogRepository.findAllByCatalogId(category.getId()))
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    public CatalogItem getById(Long id) {
        return catalogRepository
                .findById(id)
                .orElseThrow(() -> new CatalogItemNotFoundException(id));
    }

    @Override
    public CatalogItem create(CatalogItem catalogItem) {
        return catalogRepository.save(catalogItem);
    }

    @Override
    public CatalogItem update(Long id, CatalogItem catalogItem) {
        return catalogRepository
                .findById(id)
                .map((item) -> {
                    CatalogItem updatedCatalogItem = new CatalogItem(
                            item.getId(),
                            catalogItem.getName(),
                            catalogItem.getDescription(),
                            catalogItem.getPrice(),
                            catalogItem.getCategories()
                    );
                    return catalogRepository.save(updatedCatalogItem);
                })
                .orElseThrow(() -> new CatalogItemNotFoundException(id));
    }

    @Override
    public Long deleteById(Long id) {
        return catalogRepository
                .findById(id)
                .map(item -> {
                    catalogRepository.deleteById(item.getId());
                    return item.getId();
                })
                .orElseThrow(() -> new CatalogItemNotFoundException(id));
    }
}
