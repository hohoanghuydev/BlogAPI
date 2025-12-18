package com.example.blog_api.repository;

import com.example.blog_api.entity.TagPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagPostRepository extends JpaRepository<TagPost, Long> {
}
