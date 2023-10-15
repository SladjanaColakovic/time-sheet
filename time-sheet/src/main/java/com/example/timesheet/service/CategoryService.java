package com.example.timesheet.service;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.ICategoryRepository;
import com.example.timesheet.core.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.update(category);
    }
}
