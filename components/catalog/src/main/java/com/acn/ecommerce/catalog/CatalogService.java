package com.acn.ecommerce.catalog;

import com.acn.ecommerce.catalog.data.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService {

    Page<CatalogItem> list(Pageable pageable);

    List<CatalogItem> listByCatalogId(Long categoryId, Pageable pageable);

    CatalogItem getById(Long id);

    CatalogItem create(CatalogItem catalogItem);

    CatalogItem update(Long id, CatalogItem catalogItem);

    Long deleteById(Long id);

}
