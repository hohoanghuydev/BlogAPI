package com.example.blog_api.controller;

import com.example.blog_api.dto.UserRequestDto;
import com.example.blog_api.dto.UserResponseDto;
import com.example.blog_api.entity.User;
import com.example.blog_api.repository.UserRepository;
import com.example.blog_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponse = userService.register(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto userRequestDto) {
        String user = userService.login(userRequestDto);
        return ResponseEntity.ok(user);
    }
}
