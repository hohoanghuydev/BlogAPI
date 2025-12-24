package com.example.blog_api.service;

import com.example.blog_api.dto.*;
import com.example.blog_api.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    //CRUD
    DetailPostResponseDto create(PostCreateRequestDto request);
    PostResponseDto update(Long id, PostUpdateRequestDto request);
    void delete(Long id);
    DetailPostResponseDto findById(Long id);

    //Query
    List<PostResponseDto> findAll(Pageable pageable, String searchText);
    List<PostResponseDto> findAllPostByTag(Pageable pageable, String tag);
}
