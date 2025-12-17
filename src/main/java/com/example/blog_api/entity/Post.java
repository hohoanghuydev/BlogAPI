package com.example.blog_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank
    private String title;


    private String description;

    @NotBlank
    private String content;


    private LocalDate publishDate = LocalDate.now();
    private String imageUrl;//Extract Object ImagePost(author, where) to List

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<TagPost> tags = new HashSet<>();//

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();//


    //@Enumerable(EnumType.String)????
    //PostStatus status (draft, published, deleted)
    //updatedAt
    //viewCount (recommend)
    //slug (SEO, Url đẹp)
}
