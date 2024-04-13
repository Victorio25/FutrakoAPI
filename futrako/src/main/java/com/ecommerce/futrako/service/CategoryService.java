package com.ecommerce.futrako.service;

import com.ecommerce.futrako.dto.RequestCategoryDto;
import com.ecommerce.futrako.dto.ResponseCategoryDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.Category;
import com.ecommerce.futrako.repository.ICategoryRepository;
import com.ecommerce.futrako.service.interfaces.ICategoryService;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    Mapper mapper;

    @Override
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = iCategoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categories);
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        try {
            Category category = iCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            iCategoryRepository.delete(category);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Category Deleted");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCategory(Long id, RequestCategoryDto categoryDto) {
        try {
            Category category = iCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not Found"));
            category.setDescription(categoryDto.getDescription());
            category.setName(categoryDto.getName());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(iCategoryRepository.save(category), ResponseCategoryDto.class));


        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> postCategory(RequestCategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iCategoryRepository.save(mapper.getMapper().map(categoryDto, Category.class)));
    }
}