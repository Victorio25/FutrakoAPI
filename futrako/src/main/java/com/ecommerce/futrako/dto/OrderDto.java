package com.ecommerce.futrako.dto;


import com.ecommerce.futrako.model.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class OrderDto {

    private Long id;

    private Date orderDate;

    private String paymentMethod;

    private String customerName;

    private OrderStatus orderStatus;

    private Set<ItemDto> items;

    private String shipmentProvider;

}
