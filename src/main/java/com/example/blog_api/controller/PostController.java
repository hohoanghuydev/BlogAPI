package com.example.blog_api.controller;

import com.example.blog_api.dto.DetailPostResponseDto;
import com.example.blog_api.dto.PostCreateRequestDto;
import com.example.blog_api.dto.PostRequestDto;
import com.example.blog_api.dto.PostResponseDto;
import com.example.blog_api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;
    private static final int POSITIVE_SIZE = 1;
    private static final int POSITIVE_PAGE = 1;

    private int validatePage(Long page) {
        if (page == null)
            return DEFAULT_PAGE_NUMBER;

        if (page < POSITIVE_PAGE) {
            throw new IllegalArgumentException("Page number must be greater than 0.");
        }

        return page.intValue();
    }

    private int validateSize(Long size) {
        if (size == null)
            return DEFAULT_PAGE_SIZE;

        if (size < POSITIVE_SIZE || size > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("Page size must be greater than 0 and least than 100");
        }

        return size.intValue();
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(
            @RequestParam(required = false) Long page,
            @RequestParam(required = false) Long size,
            @RequestParam(required = false, name = "search") String searchText
    ) {
        int pageNumber = validatePage(page);
        int pageSize = validateSize(size);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        List<PostResponseDto> posts = postService.findAll(pageable, searchText);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<DetailPostResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto request) {
        DetailPostResponseDto post = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
