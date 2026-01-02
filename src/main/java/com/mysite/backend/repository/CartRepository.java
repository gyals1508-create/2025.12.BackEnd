package com.mysite.backend.repository;

import com.mysite.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByShoppingDate(LocalDate shoppingDate);
    List<Cart> findByTextContaining(String text);
    boolean existsByTextAndIsFavoriteTrue(String text);
    List<Cart> findAllByText(String text);

    // [필수] 이 메서드가 있어야 컨트롤러 에러가 사라져!
    @Query("SELECT c FROM Cart c WHERE c.shoppingDate = :shoppingDate OR c.isFavorite = true")
    List<Cart> findAllByShoppingDateOrIsFavoriteTrue(@Param("shoppingDate") LocalDate shoppingDate);
}