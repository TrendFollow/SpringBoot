package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDTO;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    PizzaService pizzaService;

    @GetMapping("/api/pizzas") // 모든 피자 조회
    public ResponseEntity<List<PizzaDTO>> lookup(){
        List<PizzaDTO> pizza = pizzaService.lookup();
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @GetMapping("/api/pizzas/{name}") // 특정 이름 피자 조회
    public ResponseEntity<PizzaDTO> lookupName(@PathVariable String name){
        PizzaDTO pizza = pizzaService.lookupName(name);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @PostMapping("/api/pizzas") // 피자 목록 생성
    public ResponseEntity<PizzaDTO> create(@RequestBody PizzaDTO dto){
        PizzaDTO pizza = pizzaService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @PatchMapping("/api/pizzas/{id}") // 피자 수정
    public ResponseEntity<PizzaDTO> update(@PathVariable Long id,@RequestBody PizzaDTO dto){
        PizzaDTO dtos = pizzaService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @DeleteMapping("/api/pizzas/{id}") // 피자 삭제
    public ResponseEntity<PizzaDTO> delete(@PathVariable Long id){
        PizzaDTO dto = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



}
