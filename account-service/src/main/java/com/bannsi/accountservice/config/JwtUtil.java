package com.bannsi.accountservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")       
    private String secret;
}
