package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Category;

public interface CategoryServiceI {
    public Category create(Category category);
    public Category getById(Long id);
}
