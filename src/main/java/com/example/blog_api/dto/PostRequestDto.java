package com.example.blog_api.dto;

import com.example.blog_api.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostRequestDto {
    @NotBlank(message = "Title can not empty")
    private String title;

    private String description;

    @NotBlank(message = "Content can not empty")
    private String content;
    private String imageUrl;//Extract Object ImagePost(author, where) to List

    @NotNull(message = "User id can not null")
    private Long userId;
}
