package com.mysite.backend.repository;

import com.mysite.backend.entity.Cart; // ★ Cart 엔티티 참조
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // 날짜로 장보기 목록 찾아오는 기능 (엔티티 타입 변경)
    List<Cart> findAllByShoppingDate(LocalDate shoppingDate);
}