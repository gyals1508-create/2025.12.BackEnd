package com.mysite.backend.controller;

import com.mysite.backend.entity.Cart;
import com.mysite.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartRepository repository;

    // 1. [수정] 해당 날짜의 목록만 조회 (즐겨찾기 섞어오기 금지)
    @GetMapping
    public List<Cart> getList(@RequestParam LocalDate date) {
        return repository.findAllByShoppingDate(date);
    }

    // 2. [추가] 즐겨찾기 목록 별도 조회 (프론트 fetchFavorites용)
    @GetMapping("/favorites")
    public List<Cart> getFavorites() {
        return repository.findByIsFavoriteTrue();
    }

    @GetMapping("/search")
    public List<Cart> search(@RequestParam String text) {
        return repository.findByTextContaining(text);
    }

    // 3. [수정] 생성 로직 개선 (숨겨진 즐겨찾기 재활용)
    @PostMapping
    @Transactional
    public Cart create(@RequestBody Cart item) {
        List<Cart> sameNameItems = repository.findAllByText(item.getText());

        for (Cart exists : sameNameItems) {
            // A. 이미 오늘 목록에 있으면 -> 개수 증가
            if (exists.getShoppingDate() != null
                    && exists.getShoppingDate().equals(item.getShoppingDate())
                    && !exists.getIsBought()) {
                exists.setCount(exists.getCount() + 1);
                return repository.save(exists);
            }

            // B. [신규 로직] 즐겨찾기해놔서 DB에 있지만 날짜가 없는(숨겨진) 항목 재활용 -> 중복 생성 방지!
            if (exists.getShoppingDate() == null) {
                exists.setShoppingDate(item.getShoppingDate());
                exists.setIsBought(false);
                exists.setCount(1);
                // 즐겨찾기 상태는 유지됨
                return repository.save(exists);
            }
        }

        // C. 아예 없으면 새로 생성
        boolean isFav = repository.existsByTextAndIsFavoriteTrue(item.getText());
        item.setIsFavorite(isFav);
        item.setCount(1);
        return repository.save(item);
    }

    @PutMapping("/{id}")
    @Transactional
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        Cart target = repository.findById(id).orElseThrow(() -> new RuntimeException("Error"));

        // 즐겨찾기 상태 동기화
        if (target.getIsFavorite() != item.getIsFavorite()) {
            repository.findAllByText(target.getText()).forEach(c -> c.setIsFavorite(item.getIsFavorite()));
        }

        target.setIsBought(item.getIsBought());
        target.setShoppingDate(item.getShoppingDate());
        target.setText(item.getText());
        target.setCount(item.getCount());
        target.setIsFavorite(item.getIsFavorite());
        return repository.saveAndFlush(target);
    }

    // 4. [핵심 수정] 삭제 로직 (즐겨찾기는 살려둠)
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Cart target = repository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        if (target.getIsFavorite()) {
            // [중요] 즐겨찾기라면 DB 삭제 금지! -> 날짜만 지워서 리스트에서 숨김
            target.setShoppingDate(null);
            target.setIsBought(false);
            target.setCount(1);
            repository.save(target);
        } else {
            // 즐겨찾기가 아니면 진짜 삭제
            repository.delete(target);
        }
    }
}