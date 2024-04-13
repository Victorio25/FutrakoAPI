package com.ecommerce.futrako.service;

import com.ecommerce.futrako.exception.ResourceFoundException;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.listing.RoleName;
import com.ecommerce.futrako.model.Role;
import com.ecommerce.futrako.repository.IRoleRepository;
import com.ecommerce.futrako.service.interfaces.IRoleService;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class RoleService implements IRoleService {
    @Autowired
    private Mapper mapper;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Role findByName(RoleName roleName) {
        Role role = iRoleRepository.findByName(roleName);
        if (role == null) {
            throw new ResourceNotFoundException("Role not found");
        }
        return role;
    }

    @Override
    public ResponseEntity<?> postRole(Role role) throws ResourceFoundException {
        if (iRoleRepository.existsByName(role.getName())) {
            throw new ResourceFoundException("Role already exists");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iRoleRepository.save(role));
    }

    @Override
    public Role createRole(Role role) throws ResourceFoundException {
        if (iRoleRepository.existsByName(role.getName())) {
            throw new ResourceFoundException("Role already exists");
        }
        return iRoleRepository.save(role);
    }

}