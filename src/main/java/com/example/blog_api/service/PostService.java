package com.example.blog_api.service;

import com.example.blog_api.dto.DetailPostResponseDto;
import com.example.blog_api.dto.PostCreateRequestDto;
import com.example.blog_api.dto.PostRequestDto;
import com.example.blog_api.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    //CRUD
    DetailPostResponseDto create(PostCreateRequestDto request);
    PostResponseDto update(Long id, PostRequestDto request);
    void delete(Long id);
    PostResponseDto findById(Long id);

    //Query
    List<PostResponseDto> findAll();
}
