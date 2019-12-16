package com.acn.ecommerce.catalog.tests;

import com.acn.ecommerce.catalog.models.CatalogItem;
import com.acn.ecommerce.catalog.repositories.CatalogRepository;
import com.acn.ecommerce.catalog.services.CatalogServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Catalog Unit Tests")
@ExtendWith(MockitoExtension.class)
public class CatalogServiceImplTest {

    @Mock
    CatalogRepository catalogRepository;

    @InjectMocks
    CatalogServiceImpl catalogService;

    @Test
    @DisplayName("Catalog Service List Items Test")
    public void listTest() {

        Pageable pageable = PageRequest.of(1, 1);
        CatalogItem item = CatalogItem.builder()
                .id(1L)
                .name("itemName")
                .description("itemDesc")
                .price(5.99f)
                .categories(new ArrayList<>())
                .build();

        Page<CatalogItem> expectedPage = new PageImpl<>(Collections.singletonList(item), pageable, 1);

        when(catalogRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<CatalogItem> result = catalogService.list(pageable);

        assertEquals(result, expectedPage);
        verify(catalogRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Catalog Service List Items by Category Test")
    public void listByCatalogIdTest() {
        List<CatalogItem> items = Arrays.asList(
                CatalogItem.builder()
                        .id(1L)
                        .name("item1")
                        .description("item1desc")
                        .price(1.00f)
                        .categories(new ArrayList<>())
                        .build(),
                CatalogItem.builder()
                        .id(2L).name("item2")
                        .description("item2desc")
                        .price(2.00f)
                        .categories(new ArrayList<>())
                        .build()
        );

        when(catalogRepository.findAllByCatalogId(anyLong())).thenReturn(items);

        List<CatalogItem> result = catalogService.listByCatalogId(1L);

        assertEquals(items, result);
        verify(catalogRepository, times(1)).findAllByCatalogId(anyLong());
    }

    @Test
    @DisplayName("Catalog Service List Items by Category Test")
    public void listByCatalogIdTest__categoryNotFound() {
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void getByIdTest() {
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void getByIdTest__itemNotFound() {
    }

    @Test
    @DisplayName("Catalog Service Create Catalog Item Test")
    public void createTest() {
    }

    @Test
    @DisplayName("Catalog Service Create Catalog Item Test")
    public void createTest__categoryNotFound() {
    }

    @Test
    @DisplayName("Catalog Service Update Item Test")
    public void updateTest() {
    }

    @Test
    @DisplayName("Catalog Service Update Item Test")
    public void updateTest__categoryNotFound() {
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void updateTest__itemNotFound() {
    }

    @Test
    @DisplayName("Catalog Service Delete Item by Id Test")
    public void deleteByIdTest() {

        doNothing().when(catalogRepository).deleteById(anyLong());

        catalogService.deleteById(1L);

        verify(catalogRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Catalog Service Delete Item by Id Test")
    public void deleteByIdTest__itemNotFound() {

    }

}
