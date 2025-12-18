package com.example.blog_api.service;

import com.example.blog_api.dto.TagRequestDto;
import com.example.blog_api.dto.TagResponseDto;
import com.example.blog_api.entity.Tag;
import com.example.blog_api.exception.DuplicateTagNameException;
import com.example.blog_api.exception.ResourceNotFound;
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

    private Tag getTag(Long id){
        return tagRepo.findById(id).orElseThrow(() -> new ResourceNotFound(String.format(ErrorMessage.ERROR_TAG_NOT_FOUND, id)));
    }

    private Tag getTag(String tagName) {//? tai sao khong throw binh thuong duoc
        Tag tag = tagRepo.findByTagName(tagName);

        if (tag == null){
            throw new ResourceNotFound(String.format(ErrorMessage.ERROR_TAG_NOT_FOUND, tagName));
        }

        return tag;
    }

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

    @Override
    public Boolean checkExists(String tagName) {
        return tagRepo.existsByTagName(tagName);
    }

    @Override
    public TagResponseDto findByInfo(Long id) {
        Tag tag = getTag(id);
        return TagMapper.toResponse(tag);
    }

    @Override
    public TagResponseDto findByInfo(String name) {
        Tag tag = getTag(name);
        return TagMapper.toResponse(tag);
    }
}
