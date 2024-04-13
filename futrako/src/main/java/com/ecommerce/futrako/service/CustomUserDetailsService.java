package com.ecommerce.futrako.service;

import com.ecommerce.futrako.config.CustomUserDetails;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.User;
import com.ecommerce.futrako.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findOptionalByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new CustomUserDetails(user);

    }
}