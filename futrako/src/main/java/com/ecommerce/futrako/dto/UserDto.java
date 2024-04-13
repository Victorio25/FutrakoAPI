package com.ecommerce.futrako.dto;

import com.ecommerce.futrako.model.Role;
import com.ecommerce.futrako.model.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long time;

    private String address;

    private String phone;

    private String avatar;

    private Role role;

    private UserStatus userStatus;

}
