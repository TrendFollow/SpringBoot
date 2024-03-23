package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDTO;
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

    public static Comment createComment(CommentDTO dto, Article article) {
        // 예외 발생
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패!!, 댓글의 id가 없어야 합니다.");
        }
        if(dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패!!, 게시글의 id가 틀렸습니다.");
        }

        // 엔티티 생성 및 반환
        return new Comment(dto.getId(),article,dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDTO dto) {
        // 예외 발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        }
        // 객체 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}