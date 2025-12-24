package com.example.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequestDto {
    @NotBlank(message = "Title can not empty")
    private String title;

    private String description;

    @NotBlank(message = "Content can not empty")
    private String content;
    private String imageUrl;//Extract Object ImagePost(author, where) to List
}
