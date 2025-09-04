package com.handyhub.user.service;

import com.handyhub.user.dto.LoginRequest;
import com.handyhub.user.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public LoginResponse login(LoginRequest request);
}
