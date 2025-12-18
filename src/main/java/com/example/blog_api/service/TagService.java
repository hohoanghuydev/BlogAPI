package com.example.blog_api.service;

import com.example.blog_api.dto.TagRequestDto;
import com.example.blog_api.dto.TagResponseDto;

import java.util.List;

public interface TagService {
    //CRUD
    TagResponseDto create(TagRequestDto request);
    TagResponseDto findByInfo(Long id);
    TagResponseDto findByInfo(String name);

    //Query
    List<TagResponseDto> findAll();
    Boolean checkExists(String tagName);
}
