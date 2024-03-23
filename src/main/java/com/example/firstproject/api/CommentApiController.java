package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDTO;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;
    // 1. 댓글조회(게시물 번호로 모든 댓글 조회)
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDTO> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 2. 댓글생성(게시물 번호에 댓글 생성)
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable Long articleId, @RequestBody CommentDTO dto){
        // 서비스에 위임
        CommentDTO createdDto = commentService.create(articleId, dto);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }


    // 3. 댓글수정
    @PatchMapping("api/comments/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO dto){
        // 서비스에 위임
        CommentDTO updatedDto = commentService.update(id, dto);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 4. 댓글삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id){
        // 서비스에 위임
        CommentDTO deleted = commentService.delete(id);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
