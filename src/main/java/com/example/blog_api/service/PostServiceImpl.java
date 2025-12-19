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
import com.example.blog_api.repository.TagRepository;
import com.example.blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TagRepository tagRepo;//? Không nên gọi service?

    @Autowired
    private TagPostRepository tagPostRepo;//?

    @Autowired
    private UserRepository userRepo;//? Coi co can transaction khong

    private Post getPostById(Long id) {
        return postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(String.format(ErrorMessage.ERROR_POST_NOT_FOUND, id))
        );
    }

    private Set<Tag> findOrCreateTag(Set<String> tagsName) {
        Set<Tag> tags = new HashSet<>();

        tagsName.forEach(tagName -> {
            Tag tag = tagRepo.findByTagName(tagName)
                    .orElseGet(() -> tagRepo.save(Tag.builder().tagName(tagName).build()));

            tags.add(tag);
        });

        return tags;
    }

    private void saveTagsPost(Set<Tag> tags, Post post) {
        Set<TagPost> tagPosts = new HashSet<>();

        tags.forEach(tag -> {
            tagPosts.add(new TagPost(tag, post));
        });

        tagPostRepo.saveAll(tagPosts);
    }

    private User getUser(Long id) {//?
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound(String.format(ErrorMessage.ERROR_USER_NOT_FOUND, id))
        );
    }

    private Set<TagResponseDto> formatToTagsResponse(Set<Tag> tags) {
        return tags.stream().map(TagMapper::toResponse).collect(Collectors.toSet());
    }

    private Set<String> findTagsByPostId(Long postId) {
        Set<TagPost> tagPosts = tagPostRepo.findAllByPost_Id(postId);

        return tagPosts.stream().map(tagPost -> tagPost.getTag().getTagName()).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public DetailPostResponseDto create(PostCreateRequestDto request) {
        User userPost = getUser(request.getUserId());//? Chỗ này bị lặp này, có nên gọi luôn service không nhỉ

        Post createdPost = postRepo.save(PostMapper.toEntity(request, userPost));
        Set<Tag> tags = findOrCreateTag(request.getTags());
        saveTagsPost(tags, createdPost);//?

        Set<TagResponseDto> tagResponse = formatToTagsResponse(tags);
        return PostMapper.toResponseDetail(createdPost, tagResponse);
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
        Set<String> tags = findTagsByPostId(id);

        return PostMapper.toResponse(post, tags);
    }

    @Override
    public List<PostResponseDto> findAll(Pageable pageable, String searchText) {
        Page<Post> paginationPost;

        if (searchText == null || searchText.isEmpty()) {
            paginationPost = postRepo.findAll(pageable);
        } else {
            paginationPost = postRepo.findByTitleContainingIgnoreCase(pageable, searchText);
        }

        return paginationPost.map(post -> {
            Set<String> tags = findTagsByPostId(post.getPostId());
            return PostMapper.toResponse(post, tags);
        }).toList();
    }
}
