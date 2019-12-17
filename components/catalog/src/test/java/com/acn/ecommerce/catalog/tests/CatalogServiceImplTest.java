package com.acn.ecommerce.catalog.tests;

import com.acn.ecommerce.catalog.CatalogItemNotFoundException;
import com.acn.ecommerce.catalog.CatalogRepository;
import com.acn.ecommerce.catalog.CatalogServiceImpl;
import com.acn.ecommerce.catalog.data.CatalogItem;
import com.acn.ecommerce.category.CategoryNotFoundException;
import com.acn.ecommerce.category.CategoryRepository;
import com.acn.ecommerce.category.data.Category;
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

        List<CatalogItem> result = catalogService.listByCatalogId(category_1.getId(), PageRequest.of(1, 1));

        assertEquals(items, result);
        verify(catalogRepository, times(1)).findAllByCatalogId(anyLong());
    }

    @Test
    @DisplayName("Catalog Service List Items by Category Test")
    public void listByCatalogIdTest__categoryNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> catalogService.listByCatalogId(1L, PageRequest.of(1, 1)));
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void getByIdTest() {
        when(catalogRepository.findById(item_1.getId())).thenReturn(Optional.of(item_1));

        CatalogItem result = catalogService.getById(item_1.getId());

        assertEquals(result, item_1);
        verify(catalogRepository, times(1)).findById(item_1.getId());
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void getByIdTest__itemNotFound() {

        when(catalogRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CatalogItemNotFoundException.class, () -> catalogService.getById(anyLong()));
    }

    @Test
    @DisplayName("Catalog Service Create Catalog Item Test")
    public void createTest() {
        CatalogItem savedItem = CatalogItem.builder()
                .id(1L)
                .name("item1")
                .description("item1Desc")
                .categories(categories_1)
                .price(5.99f)
                .build();

        CatalogItem inputItem = CatalogItem.builder()
                .name("item1")
                .description("item1Desc")
                .categories(categories_1)
                .price(5.99f)
                .build();

        when(catalogRepository.save(inputItem)).thenReturn(savedItem);

        CatalogItem result = catalogService.create(inputItem);

        assertEquals(result, savedItem);
        verify(catalogRepository, times(1)).save(inputItem);
    }

    @Test
    @DisplayName("Catalog Service Create Catalog Item Test")
    public void createTest__categoryNotFound() {

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(catalogRepository.save(any())).thenReturn(item_1);

        assertThrows(CategoryNotFoundException.class, () -> catalogService.create(item_1));
    }

    @Test
    @DisplayName("Catalog Service Update Item Test")
    public void updateTest() {

        CatalogItem originalItem = CatalogItem.builder()
                .id(1L)
                .name("item1o")
                .description("itemDesc1o")
                .price(5.99f)
                .categories(categories_1)
                .build();

        CatalogItem updatedItem = CatalogItem.builder()
                .id(1L)
                .name("item1u")
                .description("itemDesc1u")
                .price(6.99f)
                .categories(categories_2)
                .build();

        when(catalogRepository.save(originalItem)).thenReturn(updatedItem);
        when(categoryRepository.saveAll(categories_2)).thenReturn(categories_2);

        CatalogItem result = catalogService.update(originalItem.getId(), updatedItem);

        assertEquals(result, updatedItem);
    }

    @Test
    @DisplayName("Catalog Service Update Item Test")
    public void updateTest__categoryNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(catalogRepository.save(any())).thenReturn(item_1);

        assertThrows(CategoryNotFoundException.class, () -> catalogService.update(item_1.getId(), item_1));
    }

    @Test
    @DisplayName("Catalog Service Get by Id Test")
    public void updateTest__itemNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category_1));
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(catalogRepository.save(any())).thenReturn(item_1);

        assertThrows(CatalogItemNotFoundException.class, () -> catalogService.update(item_1.getId(), item_1));
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
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.empty());
        doNothing().when(catalogRepository).deleteById(anyLong());

        assertThrows(CatalogItemNotFoundException.class, () -> catalogService.deleteById(1L));
    }

}
