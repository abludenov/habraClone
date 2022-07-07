package com.javamentor.backend.controller;

import com.javamentor.backend.model.dto.UserLoginDto;
import com.javamentor.backend.security.jwt.JwtUtills;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "LoginController", tags = {"Контроллер для авторизации"})
public class LoginController {

    private final JwtUtills jwtUtills;

    public LoginController(JwtUtills jwtUtills) {
        this.jwtUtills = jwtUtills;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto loginDto) {
        try {
            String token = jwtUtills.validate(loginDto);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException f) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Неверные данные");
        }
    }
}
