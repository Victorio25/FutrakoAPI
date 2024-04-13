package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.exception.ResourceFoundException;
import com.ecommerce.futrako.listing.RoleName;
import com.ecommerce.futrako.model.Role;
import org.springframework.http.ResponseEntity;

public interface IRoleService {
    Role findByName(RoleName roleName);

    ResponseEntity<?> postRole(Role role) throws ResourceFoundException;

    Role createRole(Role role) throws ResourceFoundException;
}
