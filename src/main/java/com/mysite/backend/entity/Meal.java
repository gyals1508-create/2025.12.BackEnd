package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate; // 날짜 전용 타입
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // DB에는 'menu_name'으로 저장되고, 리액트와는 'text'로 통신함
    @Column(name = "menu_name")
    private String text;

    // 아까 SQL로 추가한 컬럼들 연결
    private String mealType; // 아침, 점심...
    private LocalDate mealDate; // 2025-12-30

    private LocalDateTime createdAt = LocalDateTime.now();
}