package com.ecommerce.futrako.service;

import com.ecommerce.futrako.dto.LoginUserDto;
import com.ecommerce.futrako.dto.PatchUserDto;
import com.ecommerce.futrako.dto.ResponseUserDto;
import com.ecommerce.futrako.dto.UserDto;
import com.ecommerce.futrako.exception.ResourceFoundException;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.exception.UserNotAllowedException;
import com.ecommerce.futrako.listing.RoleName;
import com.ecommerce.futrako.model.Role;
import com.ecommerce.futrako.model.User;
import com.ecommerce.futrako.repository.IUserRepository;
import com.ecommerce.futrako.service.interfaces.IAuthorizationService;
import com.ecommerce.futrako.service.interfaces.IRoleService;
import com.ecommerce.futrako.utils.JwtUtil;
import com.ecommerce.futrako.utils.Mapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class AuthorizationService implements IAuthorizationService {
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private Mapper mapper;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<?> save(UserDto requestUserDto) {
        try {
            return createUser(requestUserDto, RoleName.ROLE_USER);
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> saveAdmin(UserDto requestUserDto) {
        try {
            return createUser(requestUserDto, RoleName.ROLE_ADMIN);
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    public ResponseUserDto rollbackSoftDelete(UserDto requestUserDto) {
        User user = userRepository.findByEmail(requestUserDto.getEmail());
        user.setSoftDeleted(false);
        String token = this.authenticate(requestUserDto.getEmail(), requestUserDto.getPassword());
        ResponseUserDto responseUserDto = mapper.getMapper().map(userRepository.save(user), ResponseUserDto.class);
        responseUserDto.setToken(token);
        return responseUserDto;
    }

    public ResponseEntity<?> createNewUser(UserDto requestUserDto, RoleName roleName) {
        User user = mapper.getMapper().map(requestUserDto, User.class);
        user.setAvatar("https://unavatar.io/" + requestUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
        try {
            Role role = roleService.findByName(roleName);
            user.setRole(role);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        user.setCreationDate(new Date());
        User userSaved = userRepository.save(user);

        try {
            String token = this.authenticate(requestUserDto.getEmail(), requestUserDto.getPassword());

            ResponseUserDto responseUserDto = mapper.getMapper().map(userSaved, ResponseUserDto.class);
            responseUserDto.setToken(token);

            return ResponseEntity.status(HttpStatus.OK).body(responseUserDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    private String authenticate(String email, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.create(authentication);
    }

    @Override
    public ResponseEntity<?> update(PatchUserDto patchUserDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(Objects.equals(userRepository.findByEmail(auth.getName()).getId(), userRepository.findByEmail(patchUserDto.getEmail()).getId()))) {
            throw new AccessDeniedException("You can not modify another user´s details");
        }
        User user = userRepository.findByEmail(patchUserDto.getEmail());

//        user.setPassword(passwordEncoder.encode(patchUserDto.getPassword()));
        user.setFirstName(patchUserDto.getFirstName());
        user.setLastName(patchUserDto.getLastName());
        user.setEmail(user.getEmail());
        user.setAddress(patchUserDto.getAddress());

        User userUpdated = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(userUpdated, PatchUserDto.class));

    }

    @Override
    public UserDto findByEmail(String email) throws ResourceNotFoundException {
        if (!userRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("User not found");
        }
        User user = userRepository.findByEmail(email);
        return mapper.getMapper().map(user, UserDto.class);
    }

    @Override
    public UserDto getUserAuthenticated() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return mapper.getMapper().map(userRepository.findByEmail(email), UserDto.class);
    }

    @Override
    public ResponseEntity<?> login(LoginUserDto loginUser) {
        try {
            if (userRepository.existsByEmail(loginUser.getEmail())) {
                if (userRepository.findByEmail(loginUser.getEmail()).isSoftDeleted()) {
                    throw new UserNotAllowedException("User is pending deletion");
                }
            } else {
                throw new ResourceNotFoundException("User not found");
            }
            ResponseUserDto user = mapper.getMapper().map(userRepository.findByEmail(loginUser.getEmail()), ResponseUserDto.class);
            String token = this.authenticate(loginUser.getEmail(), loginUser.getPassword());
            user.setToken(token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (UserNotAllowedException | ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully");
    }

    public ResponseEntity<?> createUser(UserDto requestUserDto, RoleName role) {
        if (userRepository.existsByEmail(requestUserDto.getEmail())) {
            if (!userRepository.findByEmail(requestUserDto.getEmail()).isSoftDeleted()) {
                throw new ResourceFoundException("User email already exists");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rollbackSoftDelete(requestUserDto));
        }
        return createNewUser(requestUserDto, role);
    }
}
