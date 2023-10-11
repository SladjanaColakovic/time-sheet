package com.example.timesheet.data.impl;

import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.ICategoryRepository;
import com.example.timesheet.data.entity.CategoryEntity;
import com.example.timesheet.data.repository.CategoryJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        if(category == null) throw new ObjectNotFoundException();
        return mapper.map(category, Category.class);
    }

    @Override
    public void delete(Long id) {
       CategoryEntity category = categoryJpaRepository.findById(id).orElse(null);
       if(category == null) throw new ObjectNotFoundException();
       categoryJpaRepository.delete(category);
    }

    @Override
    public List<Category> getAll() {
        List<CategoryEntity> categories = categoryJpaRepository.findAll();
        return categories
                .stream()
                .map(element -> mapper.map(element, Category.class))
                .collect(Collectors.toList());
    }

    @Override
    public Category update(Category category) {
        CategoryEntity editing = mapper.map(category, CategoryEntity.class);
        CategoryEntity saved = categoryJpaRepository.save(editing);
        return mapper.map(saved, Category.class);
    }
}
