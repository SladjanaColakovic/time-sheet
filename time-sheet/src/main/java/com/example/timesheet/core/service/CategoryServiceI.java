package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Category;

public interface CategoryServiceI {
    Category create(Category category);
    Category getById(Long id);
}
