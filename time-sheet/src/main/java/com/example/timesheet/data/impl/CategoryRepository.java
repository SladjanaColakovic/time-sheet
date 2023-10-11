package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.repository.ICategoryRepository;
import com.example.timesheet.data.entity.CategoryEntity;
import com.example.timesheet.data.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryRepository implements ICategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;
    private final CustomMapper mapper;
    @Autowired
    public CategoryRepository(CategoryJpaRepository categoryJpaRepository, CustomMapper mapper){
        this.categoryJpaRepository = categoryJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Category create(Category category) {
        CategoryEntity newEntity = mapper.categoryToCategoryEntity(category);
        CategoryEntity saved = categoryJpaRepository.save(newEntity);
        return mapper.categoryEntityToCategory(saved);
    }

    @Override
    public Category getById(Long id) {
        CategoryEntity category = categoryJpaRepository.findById(id).orElse(null);
        if(category == null) throw new ObjectNotFoundException();
        return mapper.categoryEntityToCategory(category);
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
                .map(element -> mapper.categoryEntityToCategory(element))
                .collect(Collectors.toList());
    }

    @Override
    public Category update(Category category) {
        CategoryEntity editing = categoryJpaRepository.findById(category.getId()).orElse(null);
        if(editing == null) throw new ObjectNotFoundException();
        mapper.categoryToCategoryEntityUpdate(category, editing);
        CategoryEntity saved = categoryJpaRepository.save(editing);
        return mapper.categoryEntityToCategory(saved);
    }
}
