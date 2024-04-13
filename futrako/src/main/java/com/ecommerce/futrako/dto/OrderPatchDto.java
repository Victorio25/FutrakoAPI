package com.ecommerce.futrako.dto;

import com.ecommerce.futrako.model.OrderStatus;
import lombok.Data;

@Data
public class OrderPatchDto {

    OrderStatus orderStatus;

}
