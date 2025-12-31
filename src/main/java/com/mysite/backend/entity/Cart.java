package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 팀원 의견 반영: 길이를 30으로 제한하고 필수값 설정
    @Column(length = 30, nullable = false)
    private String text;

    // 버그 방지: 기본값을 false로 설정하여 null 포인트 에러 차단
    @Column(nullable = false)
    private Boolean isBought = false;

    @Column(name = "shopping_date")
    private LocalDate shoppingDate;
}