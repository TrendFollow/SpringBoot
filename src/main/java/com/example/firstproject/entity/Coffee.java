package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Getter
@Setter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;


    public void patch(Coffee coffee) {
        if(coffee.getName()!= null){
            this.name = coffee.name;
        }
        if(coffee.getPrice() > 0){
            this.price = coffee.price;
        }
    }
}
