package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private  ArticleRepository articleRepository;
    @Autowired
    CommentService commentService;

    @GetMapping("/articles/new") // 회원가입 창
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") // 회원 추가
    public String createArticle(ArticleForm form){
        log.info(form.toString());
//        System.out.println(form.toString());

        // 1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());


        // 2.리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}") // id 값에 맞는 목록 요청
    public String show(@PathVariable Long id, Model model){ // 매개변수로 URL id 받아오기

        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDTO> commentDTOS = commentService.comments(id);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDTOS);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }



    @GetMapping("articles") // 모든 목록 리스트 요청
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit") // 수정 페이지 요청
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록하기
        model.addAttribute("article",article);

        return "/articles/edit";
    }
    @PostMapping("/articles/update") // 수정 폼에서 전송한 데이터는 DTO로 받는다.
    public String update(ArticleForm form){ // 매개변수로 폼 데이터가 전송된다.
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. 엔티티를 DB에 데이터 가져와 수정하기
        Article target = articleRepository.findById(article.getId()).orElse(null);
        if(target != null){
            articleRepository.save(article);
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/"+article.getId();
    }

    @GetMapping("/articles/{id}/delete") // 삭제 처리 > 목록 페이지 요청
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.!!");
        }

        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

}