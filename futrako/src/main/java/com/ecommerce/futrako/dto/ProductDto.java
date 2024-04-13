package com.ecommerce.futrako.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private String description;

    private Double price;

    private Long stock;

    private Boolean isActive;

    private ResponseCategoryDto category;

}
