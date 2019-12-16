package com.acn.ecommerce.catalog.services;

import com.acn.ecommerce.catalog.models.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService {

    Page<CatalogItem> list(Pageable pageable);

    List<CatalogItem> listByCatalogId(Long id);

    CatalogItem getById(Long id);

    CatalogItem create(CatalogItem catalogItem);

    CatalogItem update(Long id, CatalogItem catalogItem);

    void deleteById(Long id);

}
