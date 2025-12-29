package com.mysite.backend.repository;

import com.mysite.backend.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
    // 날짜로 장보기 목록 찾아오는 기능
    List<ShoppingItem> findAllByShoppingDate(LocalDate shoppingDate);
}