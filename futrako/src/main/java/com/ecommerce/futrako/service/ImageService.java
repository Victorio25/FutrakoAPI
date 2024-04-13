package com.ecommerce.futrako.service;

import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.Product;
import com.ecommerce.futrako.repository.IImageRepository;
import com.ecommerce.futrako.repository.IProductRepository;
import com.ecommerce.futrako.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.ecommerce.futrako.model.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository iImageRepository;

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public ResponseEntity<?> postImageToProduct(Long id, Image image) {
        Product product = iProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        image.setProduct(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iImageRepository.save(image));
    }

    @Override
    public ResponseEntity<?> getProductImages(Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iImageRepository.findByProduct_id(id));
    }
}
