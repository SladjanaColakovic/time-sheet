package com.example.timesheet.api.impl;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.CategoryRepositoryI;
import com.example.timesheet.core.service.CategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryServiceI {
    private final CategoryRepositoryI categoryRepository;
    @Autowired
    public CategoryService(CategoryRepositoryI categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }
}
