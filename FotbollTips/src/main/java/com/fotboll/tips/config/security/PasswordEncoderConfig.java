package com.fotboll.tips.config.security;

/**
 * Created by Patrik Melander
 * Date: 2022-02-15
 * Time: 12:44
 * Project: GoNorth2
 * Copyright: MIT
 */
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
