package com.billquote.service;

import com.billquote.dto.AuthRequest;
import com.billquote.dto.AuthResponse;
import com.billquote.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
