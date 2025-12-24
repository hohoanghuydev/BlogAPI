package com.example.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TagRequestDto {
    @NotBlank(message = "Tag name can not empty")
    private String tagName;
}
