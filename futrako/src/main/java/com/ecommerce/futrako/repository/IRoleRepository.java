package com.ecommerce.futrako.repository;

import com.ecommerce.futrako.listing.RoleName;
import com.ecommerce.futrako.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(RoleName name);

    Role findByName(RoleName roleName);
}
