package com.example.candy.controller;

import com.example.candy.model.Category;
import com.example.candy.model.CategoryDTO;
import com.example.candy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id,
                                    @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findOne(id);
        if (categoryOptional.isPresent()) {
            category.setId(id);
            categoryService.save(category);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<List<CategoryDTO>> total() {
        return new ResponseEntity<>(categoryService.totalMoney(), HttpStatus.OK);
    }
}
