package com.example.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Username can not empty")
    @Size(max = 100, message = "Username's max length is 100 characters")
    private String username;

    @NotBlank(message = "Password can not empty")
    @Size(min = 3, max = 100, message = "Password must have from 3 to 100 characters")
    private String password;
}
