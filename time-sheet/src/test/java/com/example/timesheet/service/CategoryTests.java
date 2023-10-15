package com.example.timesheet.service;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.ICategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.example.timesheet.constants.CategoryConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTests {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private Category category;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testGetAll() {
        when(categoryRepository.getAll()).thenReturn(Arrays.asList(new Category(DB_ID, DB_NAME)));

        List<Category> categories = categoryService.getAll();

        assertEquals(1, categories.size());
        assertEquals(categories.get(0).getName(), DB_NAME);

        verify(categoryRepository, times(1)).getAll();
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    @Transactional
    public void testCreate() {

        Category newCategory = new Category();
        newCategory.setName(NEW_NAME);

        when(categoryRepository.getAll()).thenReturn(Arrays.asList(new Category(DB_ID, DB_NAME)));
        when(categoryRepository.create(newCategory)).thenReturn(newCategory);

        int dbSizeBeforeAdd = categoryService.getAll().size();

        Category dbCategory = categoryService.create(newCategory);

        when(categoryRepository.getAll()).thenReturn(Arrays.asList(new Category(DB_ID, DB_NAME), newCategory));

        assertNotNull(dbCategory);

        List<Category> categories = categoryService.getAll();
        assertEquals(dbSizeBeforeAdd + 1, categories.size());

        Category saved = categories.get(categories.size() - 1);

        assertEquals(saved.getName(), NEW_NAME);

        verify(categoryRepository, times(2)).getAll();
        verify(categoryRepository, times(1)).create(newCategory);
        verifyNoMoreInteractions(categoryRepository);
    }
}
