package com.example.blog_api.mapper;

import com.example.blog_api.dto.*;
import com.example.blog_api.entity.Post;
import com.example.blog_api.entity.Tag;
import com.example.blog_api.entity.User;

import java.time.LocalDate;
import java.util.Set;

public class PostMapper {
    public static Post toEntity(PostRequestDto request, User userPost) {
        return Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .publishDate(LocalDate.now())
                .imageUrl(request.getImageUrl())
                .user(userPost)
                .viewCount(0L)
                .build();
    }

    public static Post toEntity(PostCreateRequestDto request, User userPost) {
        return Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .publishDate(LocalDate.now())
                .imageUrl(request.getImageUrl())
                .user(userPost)
                .viewCount(0L)
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
                .viewCount(0L)
                .build();
    }

    public static PostResponseDto toResponse(Post post, Set<String> tags) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .publishDate(post.getPublishDate())
                .imageUrl(post.getImageUrl())
                .viewCount(post.getViewCount())
                .author(post.getUser().getUsername())//? Coi chung null
                .tags(tags)
                .build();
    }

    public static PostResponseDto toResponse(Post post) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .publishDate(post.getPublishDate())
                .imageUrl(post.getImageUrl())
                .viewCount(post.getViewCount())
                .author(post.getUser().getUsername())//? Coi chung null
                .build();
    }

    public static DetailPostResponseDto toResponseDetail(Post post, Set<TagResponseDto> tags) {
        return DetailPostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .publishDate(post.getPublishDate())
                .imageUrl(post.getImageUrl())
                .viewCount(post.getViewCount())
                .user(UserMapper.toResponseDto(post.getUser()))
                .tags(tags)//? Lay truc tiep tu post duoc khong ta
                .build();
    }
}
