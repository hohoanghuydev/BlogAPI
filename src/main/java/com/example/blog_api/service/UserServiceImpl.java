package com.example.blog_api.service;

import com.example.blog_api.dto.*;
import com.example.blog_api.entity.Post;
import com.example.blog_api.entity.User;
import com.example.blog_api.exception.DuplicateUsernameException;
import com.example.blog_api.exception.ResourceNotFound;
import com.example.blog_api.helper.ErrorMessage;
import com.example.blog_api.mapper.PostMapper;
import com.example.blog_api.mapper.UserMapper;
import com.example.blog_api.repository.PostRepository;
import com.example.blog_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    public User getUserById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound(String.format(ErrorMessage.ERROR_USER_NOT_FOUND, id)));
    }

    private Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public UserResponseDto findById(long id) {
        User user = getUserById(id);
        return UserMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        if (existsByUsername(userRequestDto.getUsername())) {
            throw new DuplicateUsernameException(String.format(ErrorMessage.ERROR_DUPLICATE_USERNAME, userRequestDto.getUsername()));
        }

        User createUser = UserMapper.toEntity(userRequestDto);
        createUser = userRepo.save(createUser);

        return UserMapper.toResponseDto(createUser);
    }

    @Override
    public UserResponseDto update(long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepo.findAll().stream().map(UserMapper::toResponseDto).toList();
    }

    @Override//? Vẫn là lặp code do không tận dụng được Post service
    public PostResponseDto createPost(Long userId, UserPostRequestDto userPostRequest) {
        User user = getUserById(userId);
        Post createdPost = postRepo.save(PostMapper.toEntity(userPostRequest, user));
        return PostMapper.toResponse(createdPost);
    }
}
