package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired // 댓글 리파지터리
    private CommentRepository commentRepository;
    @Autowired // 게시글 리파지터리
    private ArticleRepository articleRepository;


    // 1. 댓글조회(게시물 번호로 모든 댓글 조회)
    public List<CommentDTO> comments(Long articleId) {
        /*// 1. 댓글조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 2. DTO 변환
        List<CommentDTO> dtos = new ArrayList<>();
        for (int i=0; i<comments.size(); i++){
            Comment c = comments.get(i);
            CommentDTO dto = CommentDTO.createCommentDTO(c);
            dtos.add(dto);
        }
        // 3. 결과 반환*/
        return commentRepository.findByArticleId(articleId)
                .stream().map(comment -> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList());
    }

    // 2. 댓글생성(게시물 번호에 댓글 생성)
    @Transactional
    public CommentDTO create(Long articleId, CommentDTO dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글생성 실패! 대상 게시글이 없습니다."));

        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티 DB 저장
        Comment created = commentRepository.save(comment);

        // 4. DTO 변환해 반환
        return CommentDTO.createCommentDTO(created);
    }

    // 3. 댓글수정
    @Transactional
    public CommentDTO update(Long id, CommentDTO dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDTO.createCommentDTO(updated);
    }

    // 4. 댓글삭제
    @Transactional
    public CommentDTO delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 DTO 변환 후 반환
        return CommentDTO.createCommentDTO(target);

    }
}
