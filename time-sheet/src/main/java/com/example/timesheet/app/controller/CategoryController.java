package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.CategoryUpdateDTO;
import com.example.timesheet.app.dto.NewCategoryDTO;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {
    private final ICategoryService categoryService;

    private final CustomMapper mapper;
    @Autowired
    public CategoryController(ICategoryService categoryService, CustomMapper mapper){
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCategoryDTO newCategory){
        Category category = categoryService.create(mapper.newCategoryDTOToCategory(newCategory));
        return new ResponseEntity<>(mapper.categoryToCategoryDTO(category), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category category = categoryService.getById(id);
        return new ResponseEntity<>(mapper.categoryToCategoryDTO(category), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Category> categories = categoryService.getAll();
        return new ResponseEntity<>(categories.stream()
                    .map(element -> mapper.categoryToCategoryDTO(element))
                    .collect(Collectors.toList()), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryUpdateDTO editing){
        Category category = categoryService.update(mapper.categoryUpdateDTOToCategory(editing));
        return new ResponseEntity<>(mapper.categoryToCategoryDTO(category), HttpStatus.OK);
    }
}
