package com.mysite.backend.controller;

import com.mysite.backend.entity.Meal;
import com.mysite.backend.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
@CrossOrigin(origins = "*")
public class MealController {
    private final MealRepository mealRepository;

    @GetMapping
    // 리액트에서 ?date=2025-12-30 이렇게 보내면 그걸 받아서 조회함
    public List<Meal> getList(@RequestParam LocalDate date) {
        return mealRepository.findAllByMealDate(date);
    }

    @PostMapping
    public Meal create(@RequestBody Meal meal) {
        return mealRepository.save(meal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mealRepository.deleteById(id);
    }
}