package com.handyhub.user.service.impl;

import com.handyhub.shared.exception.custom.ResourceNotFoundException;
import com.handyhub.user.dto.LoginRequest;
import com.handyhub.user.dto.LoginResponse;
import com.handyhub.user.model.User;
import com.handyhub.user.repository.UserRepository;
import com.handyhub.user.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByContactInfoEmailAndPassword(request.getEmail(),request.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "email", request.getEmail()
                ));

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toSet())
        );
    }
}
