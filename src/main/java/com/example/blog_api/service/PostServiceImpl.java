package com.example.blog_api.service;

import com.example.blog_api.dto.PostRequestDto;
import com.example.blog_api.dto.PostResponseDto;
import com.example.blog_api.entity.Post;
import com.example.blog_api.entity.User;
import com.example.blog_api.exception.ResourceNotFound;
import com.example.blog_api.helper.ErrorMessage;
import com.example.blog_api.mapper.PostMapper;
import com.example.blog_api.repository.PostRepository;
import com.example.blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;//? Coi co can transaction khong

    private Post getPostById(Long id) {
        return postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(String.format(ErrorMessage.ERROR_POST_NOT_FOUND, id))
        );
    }

    @Override
    public PostResponseDto create(PostRequestDto request) {
        User userPost = userRepo.findById(request.getUserId()).orElseThrow(
                () -> new ResourceNotFound(String.format(ErrorMessage.ERROR_USER_NOT_FOUND, request.getUserId()))
        );//? Chỗ này bị lặp này, có nên gọi luôn service không nhỉ
        Post createdPost = postRepo.save(PostMapper.toEntity(request, userPost));

        return PostMapper.toResponse(createdPost);
    }

    @Override
    public PostResponseDto update(Long id, PostRequestDto request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PostResponseDto findById(Long id) {
        Post post = getPostById(id);
        return PostMapper.toResponse(post);
    }

    @Override
    public List<PostResponseDto> findAll() {
        return postRepo.findAll().stream().map(PostMapper::toResponse).toList();
    }
}
