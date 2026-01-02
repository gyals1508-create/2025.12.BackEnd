package com.mysite.backend.controller;

import com.mysite.backend.entity.Cart;
import com.mysite.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartRepository repository;

    @GetMapping
    public List<Cart> getList(@RequestParam LocalDate date) {
        // [제발! 핵심수정] 날짜 데이터뿐만 아니라 '즐겨찾기 전체'를 가져와야 삭제 후에도 즐겨찾기 함에 남음
        List<Cart> list = repository.findAllByShoppingDateOrIsFavoriteTrue(date);

        // 동일 이름 즐겨찾기 상태 동기화
        return list.stream().map(item -> {
            if (repository.existsByTextAndIsFavoriteTrue(item.getText())) {
                item.setIsFavorite(true);
            }
            return item;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public Cart create(@RequestBody Cart item) {
        if (repository.existsByTextAndIsFavoriteTrue(item.getText())) {
            item.setIsFavorite(true);
        }
        return repository.save(item);
    }

    @PutMapping("/{id}")
    @Transactional
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        Cart target = repository.findById(id).orElseThrow(() -> new RuntimeException("Error"));

        if (target.getIsFavorite() != item.getIsFavorite()) {
            repository.findAllByText(target.getText()).forEach(c -> c.setIsFavorite(item.getIsFavorite()));
        }

        target.setIsBought(item.getIsBought());
        target.setShoppingDate(item.getShoppingDate());
        target.setText(item.getText());
        target.setCount(item.getCount());
        target.setIsFavorite(item.getIsFavorite());
        return repository.save(target);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Cart target = repository.findById(id).orElseThrow(() -> new RuntimeException("Error"));

        // 즐겨찾기면 날짜만 null로 만들어서 리스트에서만 제거
        if (target.getIsFavorite()) {
            target.setShoppingDate(null);
            target.setIsBought(false);
            repository.save(target);
        } else {
            repository.deleteById(id);
        }
    }
}