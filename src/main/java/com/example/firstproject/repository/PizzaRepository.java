package com.example.firstproject.repository;

import com.example.firstproject.dto.PizzaDTO;
import com.example.firstproject.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {
    // 특정 이름의 피자 조회
    @Query(value = "SELECT * FROM pizza WHERE name=:pizzaName", nativeQuery = true)
    Optional<Pizza> findByPizzaName(String pizzaName);

}

