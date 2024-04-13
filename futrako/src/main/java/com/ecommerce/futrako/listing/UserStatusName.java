package com.ecommerce.futrako.listing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatusName {
    BANNED("User is banned and cant user any booth or service"),
    UNBANNED("User common permissions ");

    private final String name;
}
