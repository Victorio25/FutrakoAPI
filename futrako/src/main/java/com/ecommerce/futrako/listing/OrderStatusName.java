package com.ecommerce.futrako.listing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusName {

    IN_PREPARATION("in preparation"),
    DELIVERED("delivered");

    private final String name;
}
