package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;            // 대표키

//    @JsonProperty("article_id") // JSON 키를 달리 할 경우 사용
    private Long articleId;     // 댓글의 부모 id
    private String nickname;    // 댓글 단 사람
    private String body;        // 댓글 본문

    public static CommentDTO createCommentDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getArticle().getId(), comment.getNickname(), comment.getBody());
    }
}
