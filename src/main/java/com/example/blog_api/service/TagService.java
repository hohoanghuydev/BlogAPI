package com.example.blog_api.service;

import com.example.blog_api.dto.TagRequestDto;
import com.example.blog_api.dto.TagResponseDto;

import java.util.List;

public interface TagService {
    //CRUD
    TagResponseDto create(TagRequestDto request);

    //Query
    List<TagResponseDto> findAll();
}
