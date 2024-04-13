package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.dto.LoginUserDto;
import com.ecommerce.futrako.dto.PatchUserDto;
import com.ecommerce.futrako.dto.UserDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


public interface IAuthorizationService {

    ResponseEntity<?> save(UserDto requestUserDto);

    ResponseEntity<?> saveAdmin(UserDto requestUserDto) throws SQLIntegrityConstraintViolationException;

    ResponseEntity<?> update(PatchUserDto patchUserDto);

    UserDto findByEmail(String email) throws ResourceNotFoundException;

    UserDto getUserAuthenticated();

    ResponseEntity<?> login(LoginUserDto loginUser);

    ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication auth);
}

