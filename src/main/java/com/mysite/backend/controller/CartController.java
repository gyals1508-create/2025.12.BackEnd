package com.mysite.backend.controller;

import com.mysite.backend.entity.Cart;
import com.mysite.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping") // 리액트 경로는 그대로 유지
@CrossOrigin(origins = "*")
public class CartController {
    private final CartRepository repository; // ★ 주입받는 레포지토리 변경

    @GetMapping
    public List<Cart> getList(@RequestParam LocalDate date) {
        return repository.findAllByShoppingDate(date);
    }

    @PostMapping
    public Cart create(@RequestBody Cart item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        // 기존 데이터를 먼저 찾아서 필요한 부분(isBought)만 수정하는 게 안전해
        return repository.findById(id).map(existingItem -> {
            existingItem.setIsBought(item.getIsBought());
            return repository.save(existingItem);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}