package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDTO;
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
        ////////////////////////////////////////// 여기부터시작
        return null;
    }
}
