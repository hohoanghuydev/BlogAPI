package com.example.blog_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag_post")
@Getter
@Setter
@NoArgsConstructor
public class TagPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public TagPost(Tag tag, Post post) {
        this.tag = tag;
        this.post = post;
    }
}
