package com.ecommerce.futrako.security;

import com.ecommerce.futrako.utils.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        return http.cors().and()
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET, "/users/**","/products/**","/categories/**","/post/**","/images/**","/doc/swagger-ui.html").permitAll()
//                .requestMatchers("/images/toProduct/**","/doc/swagger-ui.html").permitAll()
//                .requestMatchers(HttpMethod.POST, "/products/search").permitAll()
//                .requestMatchers("/auth/**","/doc/swagger-ui.html").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        return http.authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.GET, "/users**", "/products**", "/categories**", "/post/**", "/images**","/orders**","/v3/**").permitAll()
                                .requestMatchers("/images/toProduct/**","/auth/**", "/doc/swagger-ui/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/products/search","/roles/").permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}