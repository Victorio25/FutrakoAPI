package com.ecommerce.futrako.repository;

import com.ecommerce.futrako.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserStatusRepository extends JpaRepository<UserStatus, Long> {
}
