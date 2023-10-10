package com.example.timesheet.service;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.ICategoryRepository;
import com.example.timesheet.core.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    @Autowired
    public CategoryService(ICategoryRepository categoryRepository){
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
