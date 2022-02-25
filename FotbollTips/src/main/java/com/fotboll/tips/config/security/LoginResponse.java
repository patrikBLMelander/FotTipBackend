package com.fotboll.tips.config.security;

import lombok.Setter;
import lombok.Value;
import org.springframework.http.HttpStatus;

/**
 * Created by Patrik Melander
 * Date: 2022-02-15
 * Time: 12:44
 * Project: GoNorth2
 * Copyright: MIT
 */
@Value
@Setter
public class LoginResponse {
    String username;
    HttpStatus httpStatus;
    String jwtToken;
}
