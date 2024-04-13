package com.ecommerce.futrako.service;

import com.ecommerce.futrako.dto.ResponseUserDto;
import com.ecommerce.futrako.dto.UserDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.User;
import com.ecommerce.futrako.repository.IUserRepository;
import com.ecommerce.futrako.service.interfaces.IUserService;
import com.ecommerce.futrako.utils.JwtUtil;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private Mapper mapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public ResponseEntity<?> getAll() {
        List<User> users = iUserRepository.findAll();
        List<UserDto> usersDto = users.stream().map(user -> mapper.getMapper().map(user, UserDto.class)).toList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usersDto);
    }

    @Override
    public ResponseEntity<Object> getUser(Long id) {
        User user = iUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(user, ResponseUserDto.class));
    }


    @Override
    public ResponseEntity<?> deleteUser(Long id, String token) {
        User userToDelete = iUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        iUserRepository.delete(userToDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("User deleted");
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        User userToUpdate = iUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userToUpdate.setEmail(user.getEmail());
        user.setSoftDeleted(false);
        //modificar password
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iUserRepository.save(userToUpdate));
    }

}