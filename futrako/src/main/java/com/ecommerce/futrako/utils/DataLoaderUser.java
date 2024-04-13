package com.ecommerce.futrako.utils;

import com.ecommerce.futrako.dto.UserDto;
import com.ecommerce.futrako.service.interfaces.IAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataLoaderUser implements CommandLineRunner {

    @Autowired
    private IAuthorizationService authorizationService;

    @Override
    public void run(String... args) throws Exception {

        UserDto user;

        user = UserDto.builder()
                .firstName("Victorio")
                .lastName("Sarnaglia")
                .email("victorio.sarnaglia@gmail.com")
                .password("admin")
                .address("")
                .phone("")
                .avatar("https://unavatar.io/")
                .build();
        authorizationService.save(user);
    }
}