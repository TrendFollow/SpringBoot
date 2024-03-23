package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CoffeeDTO {
    private Long id;
    private String name;
    private int price;

    public Coffee toEntity(){
        return new Coffee(id,name,price);
    }

}
