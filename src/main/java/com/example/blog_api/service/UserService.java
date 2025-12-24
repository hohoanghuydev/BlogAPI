package com.example.blog_api.service;

import com.example.blog_api.dto.*;

import java.util.List;

public interface UserService {
    //CRUD
    UserResponseDto findById(long id);
    UserResponseDto register(UserRequestDto userRequestDto);
    UserResponseDto update(long id, UserRequestDto userRequestDto);
    void delete(long id);

    //Query
    List<UserResponseDto> findAll();

    //Business
    UserResponseDto login(UserRequestDto userRequestDto);
    PostResponseDto createPost(Long userId, UserPostRequestDto userPostRequest);
}
