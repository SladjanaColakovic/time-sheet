package com.example.timesheet.infra.impl;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.CategoryRepositoryI;
import com.example.timesheet.infra.entity.CategoryEntity;
import com.example.timesheet.infra.repository.CategoryEntityRepositoryI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaCategory implements CategoryRepositoryI {

    @Autowired
    private CategoryEntityRepositoryI categoryEntityRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Category create(Category category) {
        CategoryEntity categoryEntity = mapper.map(category, CategoryEntity.class);
        CategoryEntity saved = categoryEntityRepository.save(categoryEntity);
        return mapper.map(saved, Category.class);
    }

    @Override
    public Category getById(Long id) {
        return mapper.map(categoryEntityRepository.findById(id), Category.class);
    }
}
