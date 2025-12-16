package com.billquote.controller;

import com.billquote.dto.AuthRequest;
import com.billquote.dto.AuthResponse;
import com.billquote.dto.RegisterRequest;
import com.billquote.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

 // Dans AuthController.register()
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest dto) {
    	 // AuthResponse response = authService.register(request);

        System.out.println(">>> CONTROLLER /register appelé pour email = " + dto.getEmail());
        return ResponseEntity.ok(authService.register(dto));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }


}
