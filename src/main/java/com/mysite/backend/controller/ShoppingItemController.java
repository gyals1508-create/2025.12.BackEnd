package com.mysite.backend.controller;

import com.mysite.backend.entity.ShoppingItem;
import com.mysite.backend.repository.ShoppingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping")
@CrossOrigin(origins = "*")
public class ShoppingItemController {
    private final ShoppingItemRepository repository;

    @GetMapping
    public List<ShoppingItem> getList(@RequestParam LocalDate date) {
        return repository.findAllByShoppingDate(date);
    }

    @PostMapping
    public ShoppingItem create(@RequestBody ShoppingItem item) {
        return repository.save(item);
    }

    // [구매완료] 버튼 누를 때 상태 업데이트용 (PUT)
    @PutMapping("/{id}")
    public ShoppingItem update(@PathVariable Long id, @RequestBody ShoppingItem item) {
        // ID로 찾아서 내용을 덮어쓰기 (구매완료 상태 변경 등)
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}