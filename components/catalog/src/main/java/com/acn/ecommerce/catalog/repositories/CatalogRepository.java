package com.acn.ecommerce.catalog.repositories;

import com.acn.ecommerce.catalog.models.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogItem, Long> {
}
