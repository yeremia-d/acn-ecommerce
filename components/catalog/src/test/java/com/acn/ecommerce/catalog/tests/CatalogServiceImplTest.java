package com.acn.ecommerce.catalog.tests;

import com.acn.ecommerce.catalog.exceptions.CatalogItemNotFoundException;
import com.acn.ecommerce.catalog.exceptions.CategoryNotFoundException;
import com.acn.ecommerce.catalog.models.CatalogItem;
import com.acn.ecommerce.catalog.models.Category;
import com.acn.ecommerce.catalog.repositories.CatalogRepository;
import com.acn.ecommerce.catalog.repositories.CategoryRepository;
import com.acn.ecommerce.catalog.services.CatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Catalog Unit Tests")
@ExtendWith(MockitoExtension.class)
public class CatalogServiceImplTest {

    @Mock
    CatalogRepository catalogRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CatalogServiceImpl catalogService;

    CatalogItem item_1;
    CatalogItem item_2;

    Category category_1;
    Category category_2;

    List<Category> categories_1;
    List<Category> categories_2;

    @BeforeEach
    public void initTestObjects() {
        category_1 = Category.builder()
                .id(1L)
                .name("Category1")
                .description("Category1Desc")
                .build();

        category_2 = Category.builder()
                .id(2L)
                .name("Category2")
                .description("Category2Desc")
                .build();

        categories_1 = Arrays.asList(category_1, category_2);
        categories_2 = Arrays.asList(category_2, category_1);

        item_1 = CatalogItem.builder()
                .id(1L)
                .name("item1Name")
                .description("item1Desc")
                .price(1.99f)
                .categories(categories_1)
                .build();

        item_2 = CatalogItem.builder()
                .id(2L)
                .name("item2Name")
                .description("item2Desc")
                .price(2.99f)
                .categories(categories_2)
                .build();
    }

    @Test
    @DisplayName("Catalog Service List Items Test")
    public void listTest() {

        Pageable pageable = PageRequest.of(1, 2);
        Page<CatalogItem> expectedPage = new PageImpl<>(Arrays.asList(item_1, item_2), pageable, 2);

        when(catalogRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<CatalogItem> result = catalogService.list(pageable);

        assertEquals(result, expectedPage);
        verify(catalogRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Catalog Service List Items by Category Test")
    public void listByCatalogIdTest() {

        List<CatalogItem> items = Arrays.asList(item_1, item_2);
        when(catalogRepository.findAllByCatalogId(category_1.getId())).thenReturn(items);
        when(categoryRepository.findById(category_1.getId())).thenReturn(Optional.of(category_1));

        List<CatalogItem> result = catalogService.listByCatalogId(category_1.getId());

        assertEquals(items, result);
        verify(catalogRepository, times(1)).findAllByCatalogId(anyLong());
    }

    @Test
    @DisplayName("Catalog Service List Items by Category Test")
    public void listByCatalogIdTest__categoryNotFound() {
        when(catalogRepository.findAllByCatalogId(anyLong())).thenReturn(new ArrayList<>());
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        List<CatalogItem> result = catalogService.listByCatalogId(anyLong());

        assertThrows(CategoryNotFoundException.class, () -> catalogService.listByCatalogId(anyLong()));
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void getByIdTest() {
        CatalogItem expected = CatalogItem.builder()
                .id(1L)
                .name("item")
                .description("itemDescription")
                .price(5.99f)
                .categories(new ArrayList<>())
                .build();
        when(catalogRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        CatalogItem result = catalogService.getById(expected.getId());

        assertEquals(result, expected);
        verify(catalogRepository, times(1)).findById(expected.getId());
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void getByIdTest__itemNotFound() {

        when(catalogRepository.findById(anyLong())).thenReturn(Optional.empty());

        CatalogItem result = catalogService.getById(anyLong());

        assertThrows(CatalogItemNotFoundException.class, () -> catalogService.getById(anyLong()));
    }

    @Test
    @DisplayName("Catalog Service Create Catalog Item Test")
    public void createTest() {
        CatalogItem savedItem = CatalogItem.builder().id(1L).name("item1").description("item1Desc").categories(new ArrayList<>()).price(5.99f).build();
        CatalogItem inputItem = CatalogItem.builder().name("item1").description("item1Desc").categories(new ArrayList<>()).price(5.99f).build();
        when(catalogRepository.save(inputItem)).thenReturn(savedItem);

        CatalogItem result = catalogService.create(inputItem);

        assertEquals(result, savedItem);
        verify(catalogRepository, times(1)).save(inputItem);
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
    }

    @Test
    @DisplayName("Catalog Service Delete Item by Id Test")
    public void deleteByIdTest__itemNotFound() {
    }

}
