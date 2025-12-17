package com.example.blog_api.mapper;

import com.example.blog_api.dto.PostRequestDto;
import com.example.blog_api.dto.PostResponseDto;
import com.example.blog_api.dto.UserPostRequestDto;
import com.example.blog_api.entity.Post;
import com.example.blog_api.entity.User;

import java.time.LocalDate;

public class PostMapper {
    public static Post toEntity(PostRequestDto request, User userPost) {
        return Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .publishDate(LocalDate.now())
                .imageUrl(request.getImageUrl())
                .user(userPost)
                .build();
    }

    public static Post toEntity(UserPostRequestDto request, User userPost) {
        return Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .publishDate(LocalDate.now())
                .imageUrl(request.getImageUrl())
                .user(userPost)
                .build();
    }

    public static PostResponseDto toResponse(Post post) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .publishDate(LocalDate.now())
                .imageUrl(post.getImageUrl())
                .user(UserMapper.toResponseDto(post.getUser()))//? Coi chung null
                .build();
    }
}
