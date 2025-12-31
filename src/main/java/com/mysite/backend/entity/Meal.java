package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name", length = 30, nullable = false)
    private String text;

    @Column(name = "meal_type", length = 20)
    private String mealType;

    // ★ 칼로리 필드 추가
    @Column(name = "calories")
    private Integer calories;

    @Column(name = "meal_date")
    private LocalDate mealDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        // 칼로리 값이 없으면 0으로 기본 세팅
        if (this.calories == null) {
            this.calories = 0;
        }
    }
}