package com.example.blog_api.service;

import com.example.blog_api.dto.*;
import com.example.blog_api.entity.Post;
import com.example.blog_api.entity.Tag;
import com.example.blog_api.entity.TagPost;
import com.example.blog_api.entity.User;
import com.example.blog_api.exception.ResourceNotFound;
import com.example.blog_api.helper.ErrorMessage;
import com.example.blog_api.mapper.PostMapper;
import com.example.blog_api.mapper.TagMapper;
import com.example.blog_api.repository.PostRepository;
import com.example.blog_api.repository.TagPostRepository;
import com.example.blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TagService tagService;//?

    @Autowired
    private TagPostRepository tagPostRepo;//?

    @Autowired
    private UserRepository userRepo;//? Coi co can transaction khong

    private Post getPostById(Long id) {
        return postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(String.format(ErrorMessage.ERROR_POST_NOT_FOUND, id))
        );
    }

    private Set<Tag> findOrCreateTag(Set<String> nameTags) {
        Set<Tag> tags = new HashSet<>();

        nameTags.forEach(nameTag -> {
            TagResponseDto tagResponse = tagService.checkExists(nameTag)
                    ? tagService.findByInfo(nameTag)
                    : tagService.create(new TagRequestDto(nameTag));

            tags.add(TagMapper.toEntity(tagResponse));
        });

        return tags;
    }

    private void saveTagsPost(Set<Tag> tags, Post post) {
        for (Tag tag : tags) {
            tagPostRepo.save(new TagPost(tag, post));
        }
    }

    private User getUser(Long id) {//?
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(String.format(ErrorMessage.ERROR_USER_NOT_FOUND, id))
        );
    }

    private Set<TagResponseDto> formatToTagsResponse(Set<Tag> tags) {
        return tags.stream().map(TagMapper::toResponse).collect(Collectors.toSet());
    }

    @Override
    public DetailPostResponseDto create(PostCreateRequestDto request) {
        User userPost = getUser(request.getUserId());//? Chỗ này bị lặp này, có nên gọi luôn service không nhỉ

        Post createdPost = postRepo.save(PostMapper.toEntity(request, userPost));
        Set<Tag> tags = findOrCreateTag(request.getTags());
        saveTagsPost(tags, createdPost);//?

        Set<TagResponseDto> tagResponse = formatToTagsResponse(tags);
        return PostMapper.toResponse(createdPost, tagResponse);
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
