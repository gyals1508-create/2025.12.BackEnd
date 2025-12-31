# 🍃 Pocket Life Backend (API Server)

### 📖 프로젝트 소개

Pocket Life의 핵심 데이터를 관리하는 **Spring Boot REST API 서버**입니다.
사용자별 식단, 장보기, 일정, 가계부 데이터를 안전하게 저장하며, 프론트엔드와 JSON 형식으로 통신합니다.

---

### 🛠 기술 스택 (Tech Stack)

- **Language**: Java 17
- **Framework**: Spring Boot 3.4.x
- **Build Tool**: Gradle
- **Database**: MySQL 8.x, Spring Data JPA
- **Library**: Lombok, Spring Web

---

### 🔌 API 명세 (Endpoints)

Base URL: `http://localhost:8080`

| 기능            | Method | Endpoint                        | 설명                              |
| :-------------- | :----: | :------------------------------ | :-------------------------------- |
| **식단 조회**   | `GET`  | `/api/meals?date=yyyy-MM-dd`    | 해당 날짜의 전체 식단 조회        |
| **장보기 조회** | `GET`  | `/api/shopping?date=yyyy-MM-dd` | 날짜별 장보기 목록 조회           |
| **구매 완료**   | `PUT`  | `/api/shopping/{id}`            | 물건 구매 상태(isBought) 업데이트 |

---

### 💾 데이터베이스 설정 (Database Setup)

**1. 스키마 및 테이블 생성 (VARCHAR 길이 최적화)**

```sql
CREATE DATABASE IF NOT EXISTS life_manager;
USE life_manager;

-- 식단 테이블 (Meal)
CREATE TABLE meal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(30) NOT NULL, -- 팀원 의견 반영: 길이 30으로 축소
    meal_type VARCHAR(20),
    meal_date DATE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 장보기 테이블 (Cart)
CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(30),               -- 팀원 의견 반영: 길이 30으로 축소
    is_bought TINYINT(1) DEFAULT 0,
    shopping_date DATE
);
```
