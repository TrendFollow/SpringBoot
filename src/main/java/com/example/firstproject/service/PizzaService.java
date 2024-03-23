package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDTO;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    // 모든 피자 조회
    public List<PizzaDTO> lookup() {
        List<Pizza> pizzas = pizzaRepository.findAll();

        List<PizzaDTO> dtos = pizzas.stream().map(piz->PizzaDTO.created(piz)).collect(Collectors.toList());

        return dtos;
    }

    // 특정 이름 피자 조회
    public PizzaDTO lookupName(String name) {
        Pizza target = pizzaRepository.findByPizzaName(name).orElseThrow(()->new IllegalArgumentException("없는 피자 이름입니다."));

        PizzaDTO dto = PizzaDTO.created(target);

        return dto;
    }

    // 피자 목록 생성
    @Transactional
    public PizzaDTO create(PizzaDTO dto) {
        Pizza pizza = PizzaDTO.toEntity(dto);

        PizzaDTO dtos = PizzaDTO.created(pizza);

        pizzaRepository.save(pizza);

        return dtos;
    }
    @Transactional
    public PizzaDTO update(Long id, PizzaDTO dto) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id 값이 잘못되었습니다."));

        target.patch(dto);

        PizzaDTO result = PizzaDTO.created(target);

        return result;
    }

    // 피자 삭제
    @Transactional
    public PizzaDTO delete(Long id) {
        Pizza pizza= pizzaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id가 잘못되었습니다."));

        PizzaDTO dto = PizzaDTO.created(pizza);
        pizzaRepository.delete(pizza);
        return dto;
    }
}
