package com.example.blog_api.repository;

import com.example.blog_api.entity.TagPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TagPostRepository extends JpaRepository<TagPost, Long> {

//    Set<TagPost> findAllByPost_Id(Long postId);

    @Query(value = "select * from tag_post where post_id = :id", nativeQuery = true)
    Set<TagPost> findAllByPost_Id(@Param("id") Long id);
}
