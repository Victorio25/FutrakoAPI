package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {

    ResponseEntity<?> getProduct(Long id);

    ResponseEntity<?> getProductsByCategory(Long id);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> postProduct(Product product);

    ResponseEntity<?> patchProduct(Long id, Product product);

    ResponseEntity<?> deleteProduct(Long id);

    ResponseEntity<?> getProductsBySearch(String search);
}
