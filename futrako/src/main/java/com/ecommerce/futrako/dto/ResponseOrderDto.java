package com.ecommerce.futrako.dto;


import com.ecommerce.futrako.model.OrderStatus;
import com.ecommerce.futrako.model.Product;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ResponseOrderDto {

    private Long id;

    private Date orderDate;

    private String paymentMethod;

    private OrderStatus orderStatus;

    private Set<Product> products;

    private Set<ItemDto> items;

}
