package com.ecommerce.futrako.controller;

import com.ecommerce.futrako.model.Product;
import com.ecommerce.futrako.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/")
    public ResponseEntity<?> getProducts() {
        return iProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return iProductService.getProduct(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProduct(@PathVariable Long id, @RequestBody Product product) {
        return iProductService.patchProduct(id, product);
    }

    @PostMapping("/")
    public ResponseEntity<?> postProduct(@RequestBody Product product) {
        return iProductService.postProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return iProductService.deleteProduct(id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Long id) {
        return iProductService.getProductsByCategory(id);
    }


    @GetMapping("/search")
    public ResponseEntity<?> getProductBySearch(@RequestParam String search) {
        return iProductService.getProductsBySearch(search);
    }
}
