package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.Categories;
import com.example.timesheet.app.dto.CategoryDTO;
import com.example.timesheet.app.dto.CategoryUpdateDTO;
import com.example.timesheet.app.dto.NewCategoryDTO;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CustomMapper mapper;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody NewCategoryDTO newCategory){
        Category category = categoryService.create(mapper.newCategoryDTOToCategory(newCategory));
        CategoryDTO response = mapper.categoryToCategoryDTO(category);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category category = categoryService.getById(id);
        CategoryDTO response = mapper.categoryToCategoryDTO(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Category> categories = categoryService.getAll();
        List<CategoryDTO> response = categories.stream()
                .map(mapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Categories(response), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody CategoryUpdateDTO editing){
        Category category = categoryService.update(mapper.categoryUpdateDTOToCategory(editing));
        CategoryDTO response = mapper.categoryToCategoryDTO(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
