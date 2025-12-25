package com.example.blog_api.controller;

import com.example.blog_api.dto.PostResponseDto;
import com.example.blog_api.dto.UserPostRequestDto;
import com.example.blog_api.dto.UserRequestDto;
import com.example.blog_api.dto.UserResponseDto;
import com.example.blog_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") long userId) {
        UserResponseDto userResponseDto = userService.findById(userId);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.findAll();
        return ResponseEntity.ok(userResponseDtos);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto request) {
        UserResponseDto userResponse = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("{id}/post")
    public ResponseEntity<PostResponseDto> createPost(
            @PathVariable("id") Long userId,
            @Valid @RequestBody UserPostRequestDto userPostRequest
    ) {
        PostResponseDto createdPost = userService.createPost(userId, userPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
}
