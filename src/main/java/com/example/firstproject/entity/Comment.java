package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 1씩 증가
    private Long id;            // 대표키

    @ManyToOne // 다(댓글) 대 일(게시물) 관계
    @JoinColumn(name = "article_id")
    private Article article;    // 댓글의 부모 게시글

    @Column
    private String nickname;    // 댓글 단 사람
    @Column
    private String body;        // 댓글 본문
}
