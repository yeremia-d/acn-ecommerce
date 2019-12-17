package com.acn.ecommerce.catalog.tests;

import com.acn.ecommerce.catalog.exceptions.CategoryNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void getByIdTest() throws CategoryNotFoundException {
        when(categoryRepository.findById(category_1.getId())).thenReturn(Optional.of(category_1));

        Category result = categoryService.getById(category_1.getId());

        assertEquals(category_1, result);
        verify(categoryRepository, times(1)).findById(category_1.getId());
    }

    @Test
    public void getByIdTest__categoryNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CategoryNotFoundException.class, () -> categoryService.getById(1L));

        assertTrue(exception.getMessage().contains("Category with id 1 not found or does not exist."));
    }

    @Test
    public void createTest() {
        Category createdCategory = Category.builder().name(category_1.getName()).description(category_1.getDescription()).build();
        when(categoryRepository.save(createdCategory)).thenReturn(category_1);

        Category result = categoryService.create(createdCategory);

        assertEquals(result, category_1);
        verify(categoryRepository, times(1)).save(createdCategory);
    }

    @Test
    public void updateTest() {
        when(categoryRepository.save(category_1)).thenReturn(category_1);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category_1));

        Category result = categoryService.update(category_1.getId(), category_1);

        assertEquals(category_1, result);
        verify(categoryRepository, times(1)).save(category_1);
    }

    @Test
    public void updateTest__categoryNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.update(category_1.getId(), category_1));
    }

    @Test
    public void deleteTest() throws CategoryNotFoundException {
        doNothing().when(categoryRepository).deleteById(anyLong());
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category_1));

        categoryService.deleteById(category_1.getId());

        verify(categoryRepository, times(1)).deleteById(category_1.getId());
    }

    @Test
    public void deleteTest__categoryNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteById(category_1.getId()));
    }

}