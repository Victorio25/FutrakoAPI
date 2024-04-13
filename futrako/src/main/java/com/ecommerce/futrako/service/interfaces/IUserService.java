package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> getAll();

    ResponseEntity<?> getUser(Long id);

    ResponseEntity<?> deleteUser(Long id,String token);

    ResponseEntity<?> updateUser(Long id, User user);

}