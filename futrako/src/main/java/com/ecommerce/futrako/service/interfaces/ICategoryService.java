package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.dto.RequestCategoryDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.Category;
import com.ecommerce.futrako.repository.ICategoryRepository;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryService {
    ResponseEntity<?> getAllCategories();

    ResponseEntity<?> deleteCategory(Long id);

    ResponseEntity<?> updateCategory(Long id, RequestCategoryDto categoryDto);

    ResponseEntity<Object> postCategory(RequestCategoryDto categoryDto);

}