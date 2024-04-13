package com.ecommerce.futrako.repository;

import com.ecommerce.futrako.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByCategory_id(Long id);

    List<Product> findAllByNameContainingOrDescriptionContaining(String search, String search2);

    List<Product> findByCategory_id(Long id);
}
