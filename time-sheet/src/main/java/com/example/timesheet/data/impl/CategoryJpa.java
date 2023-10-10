package com.example.timesheet.data.impl;

import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.ICategoryRepository;
import com.example.timesheet.data.entity.CategoryEntity;
import com.example.timesheet.data.repository.CategoryJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryJpa implements ICategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;
    private final ModelMapper mapper;
    @Autowired
    public CategoryJpa(CategoryJpaRepository categoryJpaRepository, ModelMapper mapper){
        this.categoryJpaRepository = categoryJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Category create(Category category) {
        CategoryEntity newEntity = mapper.map(category, CategoryEntity.class);
        CategoryEntity saved = categoryJpaRepository.save(newEntity);
        return mapper.map(saved, Category.class);
    }

    @Override
    public Category getById(Long id) {
        CategoryEntity category = categoryJpaRepository.findById(id).orElse(null);
        return mapper.map(category, Category.class);
    }
}
