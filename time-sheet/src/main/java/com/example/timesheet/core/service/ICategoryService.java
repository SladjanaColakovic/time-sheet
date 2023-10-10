package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Category;

import java.util.List;

public interface ICategoryService {
    Category create(Category category);
    Category getById(Long id);
    void delete(Long id);
    List<Category> getAll();
    Category update(Category category);
}
