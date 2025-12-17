package com.example.blog_api.controller;

import com.example.blog_api.dto.PostRequestDto;
import com.example.blog_api.dto.PostResponseDto;
import com.example.blog_api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto request) {
        PostResponseDto post = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
