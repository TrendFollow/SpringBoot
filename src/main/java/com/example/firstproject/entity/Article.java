package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity // 어노테이션이 붙은 클래스로 DB에 테이블이 형성된다. 클래스명과 동일한 테이블명
@Data
@ToString
public class Article {
    @Id // 엔티티의 대표값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 숫자가 자동으로 매겨진다.(괄호 내용 : DB가 id 자동생성)
    private Long id;

    @Column // title 필드 선언, DB 테이블의 title 열과 연결된다.
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title !=null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}
