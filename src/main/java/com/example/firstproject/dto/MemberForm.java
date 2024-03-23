package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private String title;
    private String content;
    private Long id;


    public Member toEntity(){
        return new Member(id,title,content);
    }
}
