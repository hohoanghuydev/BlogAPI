package com.example.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDto {
    @NotBlank(message = "Title can not empty")
    private String title;

    private String description;

    @NotBlank(message = "Content can not empty")
    private String content;
    private String imageUrl;
}
