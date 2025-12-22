package com.example.blog_api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
public class DetailPostResponseDto {
    private Long postId;
    private String title;
    private String description;
    private String content;
    private LocalDate publishDate;
    private String imageUrl;//Extract Object ImagePost(author, where) to List
    private Long viewCount;
    private UserResponseDto user;
    private Set<TagResponseDto> tags;
}
