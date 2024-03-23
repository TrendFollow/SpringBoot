package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(ArticleForm dto, Long id) {
        Article article = dto.toEntity();
        log.info("id:{}, article:{}",id,article.toString());

        Article target = articleRepository.findById(id).orElse(null);
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id:{}, article:{}",id,article.toString());
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    // stream : 리스트 자료구조에 요소를 순회하면서 처리한다.
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList=dtos.stream().map(dto->dto.toEntity()).collect(Collectors.toList());

        // 2. 엔티티 묶음을 DB 저장하기
        articleList.stream().forEach(article->articleRepository.save(article));

        // 3. 강제 예외 발생 시키기
        articleRepository.findById(-1L).orElseThrow(()->new IllegalArgumentException("결제 실패!"));

        // 4. 결과 값 반환하기
        return  articleList;

    }
}
