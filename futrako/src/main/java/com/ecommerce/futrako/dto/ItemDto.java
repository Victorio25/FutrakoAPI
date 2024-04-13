package com.ecommerce.futrako.dto;

import com.ecommerce.futrako.model.Product;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;

    private Long amount;

    private Product product;

}
