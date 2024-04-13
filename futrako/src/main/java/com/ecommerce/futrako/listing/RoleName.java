package com.ecommerce.futrako.listing;

import jakarta.persistence.*;
import lombok.*;
@Getter
@AllArgsConstructor
public enum RoleName {
    ROLE_ADMIN("administrator"),
    ROLE_USER("user");

    private final String name;
}
