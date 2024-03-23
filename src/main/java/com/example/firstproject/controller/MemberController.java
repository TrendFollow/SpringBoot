package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup") // 회원가입 페이지 요청
    public String singUpPage(){
        return "members/new";
    }

    @PostMapping("/join") // 회원가입 처리
    public String loginJoin(MemberForm memberForm){
        log.info(memberForm.toString());

        // 엔티티로 변환
        Member member = memberForm.toEntity();
        log.info(member.toString());

        // 리포지토리 이용해 DB 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/"+saved.getid();
    }

    @GetMapping("/members/{id}") // id 값에 맞는 목록 요청
    public String show(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",member);
        return "/members/show";
    }

    @GetMapping("/members") // 모든 목록 요청
    public String index(Model model){
        ArrayList<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList",memberList);
        return "members/index";
    }
    @GetMapping("members/{id}/edit") // 회원 수정 화면
    public String edit(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",member);
        return "/members/edit";
    }

    @PostMapping("members/update") // 수정 요청
    public String update(MemberForm form){
        Member member = form.toEntity();
        Member saved = memberRepository.findById(member.getid()).orElse(null);
        if(saved != null){
            memberRepository.save(member);
        }
        return "redirect:/members/"+member.getid();
    }

    @GetMapping("members/{id}/delete") // 삭제 처리
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        Member target = memberRepository.findById(id).orElse(null);
        if(target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제 완료~~");
        }
        return "redirect:/members";
    }



}
