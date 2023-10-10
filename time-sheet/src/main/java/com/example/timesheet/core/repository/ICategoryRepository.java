package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Category;

public interface ICategoryRepository {
    Category create(Category category);
    Category getById(Long id);
}
