package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.NewCategoryDTO;
import com.example.timesheet.app.dto.NewCountryDTO;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.service.CategoryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryServiceI categoryService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCategoryDTO newCategory){
        return new ResponseEntity<>(categoryService.create(mapper.map(newCategory, Category.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity(categoryService.getById(id), HttpStatus.OK);
    }
}
