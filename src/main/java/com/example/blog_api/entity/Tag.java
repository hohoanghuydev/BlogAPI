package com.example.blog_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @NotBlank
    @Column(unique = true)
    private String tagName;
//   String url: link to posts have same tag

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private Set<TagPost> posts = new HashSet<>();//Tại sao chỗ này cần phải khởi tạo
}
