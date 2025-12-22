package com.example.blog_api.repository;

import com.example.blog_api.entity.TagPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TagPostRepository extends JpaRepository<TagPost, Long> {

    @Query(value = "select * from tag_post where post_id = :id", nativeQuery = true)
    Set<TagPost> findAllByPost_Id(@Param("id") Long id);

    @Query(value = "select id, tag_post.tag_id, post_id from tag_post left join tag where tag_name = :name and tag_post.tag_id = tag.tag_id", nativeQuery = true)
    Page<TagPost> findAllByTag_Name(Pageable pageable, @Param("name") String name);

    void deleteByPost_PostId(Long postId);
}
