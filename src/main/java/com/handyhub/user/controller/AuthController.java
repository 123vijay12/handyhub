package com.handyhub.user.controller;

import com.handyhub.user.dto.LoginRequest;
import com.handyhub.user.dto.LoginResponse;
import com.handyhub.user.model.User;
import com.handyhub.user.repository.UserRepository;
import com.handyhub.user.service.AuthService;
import com.handyhub.user.service.impl.AuthServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse data = authService.login(request);
        return ResponseEntity.ok(data);
    }
}