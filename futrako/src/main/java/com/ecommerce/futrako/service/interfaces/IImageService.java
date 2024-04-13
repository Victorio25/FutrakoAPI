package com.ecommerce.futrako.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.ecommerce.futrako.model.Image;

public interface IImageService {

    ResponseEntity<?> postImageToProduct(Long id, Image image);

    ResponseEntity<?> getProductImages(Long id);

}
