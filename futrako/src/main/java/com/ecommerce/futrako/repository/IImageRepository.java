package com.ecommerce.futrako.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.futrako.model.Image;

import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProduct_id(Long id);
}
