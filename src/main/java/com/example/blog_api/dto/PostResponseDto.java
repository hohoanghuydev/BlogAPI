package com.example.blog_api.dto;

import com.example.blog_api.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PostResponseDto {
    private Long postId;
    private String title;
    private String description;
    private String content;
    private LocalDate publishDate;
    private String imageUrl;//Extract Object ImagePost(author, where) to List
    private UserResponseDto user;
}
