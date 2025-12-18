package com.example.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostCreateRequestDto {
    @NotBlank(message = "Title can not empty")
    private String title;
    private String description;
    @NotBlank(message = "Content can not empty")
    private String content;
    private String imageUrl;//Extract Object ImagePost(author, where) to List

    @NotNull(message = "User id can not null")
    private Long userId;
    private Set<String> tags;//? = new HashSet<>();
}
