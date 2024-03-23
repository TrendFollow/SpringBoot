package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private int price;

    public void patch(PizzaDTO dto) {
        if(dto.getId() != null){
            throw new IllegalArgumentException("id 값을 입력하시면 안됩니다.");
        }
        if(dto.getName() != null){
            this.name = dto.getName();
        }
        if(dto.getPrice() != 0){
            this.price = dto.getPrice();
        }
    }
}
