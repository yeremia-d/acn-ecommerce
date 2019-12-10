package com.acn.ecommerce.catalog.services;

import com.acn.ecommerce.catalog.models.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    @Override
    public Page<CatalogItem> list(Pageable pageable) {
        return null;
    }

    @Override
    public List<CatalogItem> listByCatalogId(Long id) {
        return null;
    }

    @Override
    public CatalogItem getById(Long id) {
        return null;
    }

    @Override
    public CatalogItem create(CatalogItem catalogItem) {
        return null;
    }

    @Override
    public CatalogItem update(CatalogItem catalogItem) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
