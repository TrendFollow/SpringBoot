package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 일반 @Controller : 뷰 페이지 반환, @RestController : 텍스트 같은 데이터 반환
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }




}
