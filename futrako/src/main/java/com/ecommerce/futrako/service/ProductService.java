package com.ecommerce.futrako.service;

import com.ecommerce.futrako.dto.ProductDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.Product;
import com.ecommerce.futrako.repository.IProductRepository;
import com.ecommerce.futrako.service.interfaces.IProductService;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private Mapper mapper;

    public ResponseEntity<?> getProduct(Long id) {
        Product product = iProductRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(product, ProductDto.class));
    }

    @Override
    public ResponseEntity<?> getProductsByCategory(Long id) {
        List<Product> products = iProductRepository.findByCategory_id(id);
        List<ProductDto> productsDto = products.stream().map(product -> mapper.getMapper().map(product, ProductDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productsDto);
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = iProductRepository.findAll();
        List<ProductDto> productsDto = products.stream().map(product -> mapper.getMapper().map(product, ProductDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productsDto);
    }

    @Override
    public ResponseEntity<?> postProduct(Product product) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iProductRepository.save(product));
    }

    @Override
    public ResponseEntity<?> patchProduct(Long id, Product product) {
        Product productToUpdate = iProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iProductRepository.save(productToUpdate));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        Product productToDelete = iProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        iProductRepository.delete(productToDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Product deleted");
    }

    @Override
    public ResponseEntity<?> getProductsBySearch(String search) {
        return null;
    }
}
