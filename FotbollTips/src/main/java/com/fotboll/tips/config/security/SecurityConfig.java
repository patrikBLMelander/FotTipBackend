package com.fotboll.tips.config.security;

/**
 * Created by Patrik Melander
 * Date: 2022-02-15
 * Time: 12:45
 * Project: GoNorth2
 * Copyright: MIT
 */

import com.fotboll.tips.config.security.filters.CustomAuthenticationFilter;
import com.fotboll.tips.config.security.filters.JWTAuthorizationFilter;
import com.fotboll.tips.config.security.filters.RequestLoggingFilter;
import com.fotboll.tips.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import static com.fotboll.tips.controller.AppConstants.API_MAPPING.*;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTIssuer jwtIssuer;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepo;
    private final CorsFilter corsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((username) -> {
            var candidate = appUserRepo.findByEmail(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found"));
            return User.builder()
                    .username(candidate.getEmail())
                    .password(candidate.getPassword())
                    .authorities(candidate.getRoleList().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                            .collect(Collectors.toSet()))
                    .build();
        }).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter();
        final JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager(), jwtIssuer);
        final CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager(), jwtIssuer);


        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(BASE_API + ADMIN + "/**").hasRole(Role.RoleConstant.ADMIN.name())
                .antMatchers(BASE_API + USERS + "/**").hasRole(Role.RoleConstant.USER.name())
                .antMatchers(BASE_API+ "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(requestLoggingFilter, CustomAuthenticationFilter.class)
                .addFilter(corsFilter)
                .addFilter(authenticationFilter)
                .addFilter(jwtAuthorizationFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
