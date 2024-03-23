package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDTO;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoffeeService {
    @Autowired
    CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDTO dto) {
        Coffee created = dto.toEntity();
        if(created.getId() != null){
            return null;
        }
        return coffeeRepository.save(created);
    }

    public Coffee update(CoffeeDTO dto, Long id) {
        // 수정본
        Coffee coffee= dto.toEntity();
        // 원본
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != coffee.getId()){
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            return null;
        }
        coffeeRepository.delete(coffee);

        return coffee;
    }
}
