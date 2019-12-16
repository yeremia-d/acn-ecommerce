package com.acn.ecommerce.catalog.tests;

import com.acn.ecommerce.catalog.models.Category;
import com.acn.ecommerce.catalog.repositories.CategoryRepository;
import com.acn.ecommerce.catalog.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    Category category_1;
    Category category_2;

    List<Category> categories;


    @BeforeEach
    public void initializeData() {

        category_1 = Category.builder().id(1L).name("Category1").description("category1Desc").build();
        category_2 = Category.builder().id(2L).name("Category2").description("category2Desc").build();

        categories = Arrays.asList(category_1, category_2);
    }

    @Test
    public void listTest() {

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.list();

        assertEquals(categories, result);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {

    }

    @Test
    public void getByIdTest__categoryNotFound() {
    }

    @Test
    public void createTest() {
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void updateTest__categoryNotFound() {
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void deleteTest__categoryNotFound() {
    }

}