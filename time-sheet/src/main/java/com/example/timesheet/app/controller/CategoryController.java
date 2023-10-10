package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.NewCategoryDTO;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.service.CategoryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {
    private final CategoryServiceI categoryService;
    private final ModelMapper mapper;
    @Autowired
    public CategoryController(CategoryServiceI categoryService, ModelMapper mapper){
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCategoryDTO newCategory){
        return new ResponseEntity<>(categoryService.create(mapper.map(newCategory, Category.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category category = categoryService.getById(id);
        if(category == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
