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
import java.util.Optional;

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
    public void testGetById() {
        when(categoryRepository.getById(DB_ID)).thenReturn(category);

        Category dbCategory = categoryService.getById(DB_ID);

        assertEquals(category, dbCategory);

        verify(categoryRepository, times(1)).getById(DB_ID);
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

    @Test
    @Transactional
    public void testUpdate() {

        when(categoryRepository.getById(DB_ID)).thenReturn(new Category(DB_ID, DB_NAME));

        Category categoryForUpdate = categoryService.getById(DB_ID);
        categoryForUpdate.setName(NEW_NAME);

        when(categoryRepository.create(categoryForUpdate)).thenReturn(categoryForUpdate);

        Category saved = categoryService.create(categoryForUpdate);

        assertNotNull(saved);

        Category dbCategory = categoryService.getById(DB_ID);
        assertEquals(dbCategory.getName(), NEW_NAME);

        verify(categoryRepository, times(2)).getById(DB_ID);
        verify(categoryRepository, times(1)).create(categoryForUpdate);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    @Transactional
    public void testDelete() {
        when(categoryRepository.getAll()).thenReturn(Arrays.asList(new Category(DB_ID, DB_NAME), new Category(DB_ID_TO_DELETE, DB_NAME_TO_DELETE)));
        doNothing().when(categoryRepository).delete(DB_ID_TO_DELETE);
        when(categoryRepository.getById(DB_ID_TO_DELETE)).thenReturn(null);

        int dbSizeBeforeDelete = categoryService.getAll().size();
        categoryService.delete(DB_ID_TO_DELETE);

        when(categoryService.getAll()).thenReturn(Arrays.asList(new Category(DB_ID, DB_NAME)));

        List<Category> categories = categoryService.getAll();
        assertEquals(dbSizeBeforeDelete -1, categories.size());

        Category dbCategory = categoryService.getById(DB_ID_TO_DELETE);
        assertNull(dbCategory);

        verify(categoryRepository, times(1)).delete(DB_ID_TO_DELETE);
        verify(categoryRepository, times(2)).getAll();
        verify(categoryRepository, times(1)).getById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(categoryRepository);
    }
}
