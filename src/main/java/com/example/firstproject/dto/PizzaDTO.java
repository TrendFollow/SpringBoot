package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PizzaDTO {
    private Long id;
    private String name;
    private int price;

    public static PizzaDTO created(Pizza piz) {
        return new PizzaDTO(piz.getId(), piz.getName(), piz.getPrice());
    }

    public static Pizza toEntity(PizzaDTO dto) {
        if(dto.getId() != null){
            throw new IllegalArgumentException("id를 입력하시면 안됩니다.");
        }
        return new Pizza(null, dto.name, dto.price);
    }
}
