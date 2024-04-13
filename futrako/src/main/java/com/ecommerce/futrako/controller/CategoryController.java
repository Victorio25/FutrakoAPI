package com.ecommerce.futrako.controller;

import com.ecommerce.futrako.dto.RequestCategoryDto;
import com.ecommerce.futrako.model.Category;
import com.ecommerce.futrako.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("/")
    public ResponseEntity<?> postCategory(@RequestBody RequestCategoryDto categoryDto) {
        return iCategoryService.postCategory(categoryDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCategory(@RequestBody RequestCategoryDto categoryDto, @PathVariable Long id) {
        return iCategoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return iCategoryService.deleteCategory(id);
    }
}
