package com.example.blog_api.mapper;

import com.example.blog_api.dto.TagRequestDto;
import com.example.blog_api.dto.TagResponseDto;
import com.example.blog_api.entity.Tag;

public class TagMapper {
    public static TagResponseDto toResponse(Tag tag) {
        return TagResponseDto.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTagName())
                .build();
    }

    public static Tag toEntity(TagRequestDto request) {
        return Tag.builder().tagName(request.getTagName()).build();
    }

    public static Tag toEntity(TagResponseDto response) {
        return Tag.builder()
                .tagId(response.getTagId())
                .tagName(response.getTagName()).build();
    }
}
