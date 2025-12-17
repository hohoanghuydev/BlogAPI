package com.example.blog_api.mapper;

import com.example.blog_api.dto.UserRequestDto;
import com.example.blog_api.dto.UserResponseDto;
import com.example.blog_api.entity.User;

public class UserMapper {
    public static UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder().id(user.getId())
                .username(user.getUsername())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toEntity(UserRequestDto userRequestDto) {
        return new User(userRequestDto.getUsername(), userRequestDto.getPassword());
    }
}
