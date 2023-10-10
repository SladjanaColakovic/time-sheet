package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.CategoryDTO;
import com.example.timesheet.app.dto.NewCategoryDTO;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.service.ICategoryService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper mapper;
    @Autowired
    public CategoryController(ICategoryService categoryService, ModelMapper mapper){
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCategoryDTO newCategory){
        Category category = categoryService.create(mapper.map(newCategory, Category.class));
        return new ResponseEntity<>(mapper.map(category, CategoryDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category category = categoryService.getById(id);
        return new ResponseEntity<>(mapper.map(category, CategoryDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Category> categories = categoryService.getAll();
        return new ResponseEntity<>(categories.stream()
                    .map(element -> mapper.map(element, CategoryDTO.class))
                    .collect(Collectors.toList()), HttpStatus.OK
                    );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDTO editing){
        Category category = categoryService.update(mapper.map(editing, Category.class));
        return new ResponseEntity<>(mapper.map(category, CategoryDTO.class), HttpStatus.OK);
    }
}
