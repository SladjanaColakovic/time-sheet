package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Category;

public interface CategoryRepositoryI {
    Category create(Category category);
    Category getById(Long id);
}
