package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor // JPA를 위한 기본 생성자
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리액트의 'text' 속성을 DB의 'menu_name' 컬럼에 매핑
    @Column(name = "menu_name", nullable = false)
    private String text;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "meal_date")
    private LocalDate mealDate;

    // 생성 시각 자동 기록 (수정 불가 설정)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 데이터가 처음 저장(Insert)되기 직전에 실행됨
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}