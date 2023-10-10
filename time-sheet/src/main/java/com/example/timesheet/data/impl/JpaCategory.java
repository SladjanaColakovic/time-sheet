package com.example.timesheet.data.impl;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.CategoryRepositoryI;
import com.example.timesheet.data.entity.CategoryEntity;
import com.example.timesheet.data.repository.CategoryEntityRepositoryI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaCategory implements CategoryRepositoryI {
    private final CategoryEntityRepositoryI categoryEntityRepository;
    private final ModelMapper mapper;
    @Autowired
    public JpaCategory(CategoryEntityRepositoryI categoryEntityRepository, ModelMapper mapper){
        this.categoryEntityRepository = categoryEntityRepository;
        this.mapper = mapper;
    }
    @Override
    public Category create(Category category) {
        CategoryEntity newEntity = mapper.map(category, CategoryEntity.class);
        return mapper.map(categoryEntityRepository.save(newEntity), Category.class);
    }

    @Override
    public Category getById(Long id) {
        return mapper.map(categoryEntityRepository.findById(id), Category.class);
    }
}
