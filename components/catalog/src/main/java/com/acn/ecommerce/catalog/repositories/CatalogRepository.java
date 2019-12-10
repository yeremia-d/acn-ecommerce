package com.acn.ecommerce.catalog.repositories;

import com.acn.ecommerce.catalog.models.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<CatalogItem, Long> {

    List<CatalogItem> findAllByCatalogId(Long catalogId);
}
