package com.ecommerce.futrako.utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper getMapper() {
        var modelMapper = new ModelMapper();
        var config = modelMapper.getConfiguration();
        config.setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}

