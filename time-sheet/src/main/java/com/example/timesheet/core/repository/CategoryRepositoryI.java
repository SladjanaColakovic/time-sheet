package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Category;
import org.springframework.stereotype.Component;

public interface CategoryRepositoryI {
    public Category create(Category category);
    public Category getById(Long id);
}
