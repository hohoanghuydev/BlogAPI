package com.example.blog_api.service;

import com.example.blog_api.dto.TagRequestDto;
import com.example.blog_api.dto.TagResponseDto;
import com.example.blog_api.entity.Tag;
import com.example.blog_api.exception.DuplicateTagNameException;
import com.example.blog_api.helper.ErrorMessage;
import com.example.blog_api.mapper.TagMapper;
import com.example.blog_api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepo;

    @Override
    public TagResponseDto create(TagRequestDto request) {
        if (tagRepo.existsByTagName(request.getTagName())) {
            throw new DuplicateTagNameException(String.format(ErrorMessage.ERROR_DUPLICATE_TAG_NAME, request.getTagName()));
        }

        Tag createdTag = tagRepo.save(TagMapper.toEntity(request));
        return TagMapper.toResponse(createdTag);
    }

    @Override
    public List<TagResponseDto> findAll() {
        return tagRepo.findAll().stream().map(TagMapper::toResponse).toList();
    }
}
