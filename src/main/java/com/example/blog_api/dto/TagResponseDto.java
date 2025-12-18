package com.example.blog_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagResponseDto {
    private Long tagId;
    private String tagName;
}
