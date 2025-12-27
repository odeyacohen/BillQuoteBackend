package com.billquote.controller;

import com.billquote.dto.CreateUserDTO;
import com.billquote.service.SocieteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/societe/users")
@RequiredArgsConstructor
public class SocieteUserController {

    private final SocieteUserService societeUserService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO dto) {
        societeUserService.createUserForMySociete(dto);
        return ResponseEntity.status(201).build();
    }
}
