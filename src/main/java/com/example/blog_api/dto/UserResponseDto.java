package com.example.blog_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
//    private LocalDateTime createdAt;
//    private Boolean active;
}
