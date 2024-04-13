package com.ecommerce.futrako.repository;

import com.ecommerce.futrako.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByEmail(String email);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
