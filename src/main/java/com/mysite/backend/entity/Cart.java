package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "cart") // ★ 테이블 이름을 cart로 연결
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;          // 물건 이름
    private Boolean isBought;     // 구매 완료 여부
    private LocalDate shoppingDate; // 장보는 날짜
}