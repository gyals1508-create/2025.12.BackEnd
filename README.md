# 🍃 Pocket Life Backend (API Server)

### 📖 프로젝트 소개

Pocket Life의 핵심 데이터를 관리하는 **Spring Boot REST API 서버**입니다.
사용자별 식단, 장보기, 일정, 가계부 데이터를 안전하게 저장하며, 프론트엔드와 JSON 형식으로 유연하게 통신합니다.

---

### 🛠 기술 스택 (Tech Stack)

- **Language**: Java 17
- **Framework**: Spring Boot 3.4.x
- **Build Tool**: Gradle
- **Database**: MySQL 8.x, Spring Data JPA
- **Library**: Lombok, Spring Web, Validation
- **Environment**: IntelliJ IDEA / VS Code

---

### 🔌 API 명세 (Endpoints)

Base URL: `http://localhost:8080` (CORS 허용 설정 완료)

| 기능            |  Method  | Endpoint                              | 설명                              |
| :-------------- | :------: | :------------------------------------ | :-------------------------------- |
| **식단 조회**   |  `GET`   | `/api/meals?date=yyyy-MM-dd`          | 해당 날짜의 전체 식단 조회        |
| **식단 기록**   |  `POST`  | `/api/meals`                          | 새로운 식단 데이터 저장           |
| **식단 삭제**   | `DELETE` | `/api/meals/{id}`                     | 특정 식단 기록 삭제               |
| **장보기 조회** |  `GET`   | `/api/shopping?date=yyyy-MM-dd`       | 날짜별 장보기 목록 조회           |
| **구매 완료**   |  `PUT`   | `/api/shopping/{id}`                  | 물건 구매 상태(isBought) 업데이트 |
| **일정 조회**   |  `GET`   | `/api/todo?userId=ID&date=yyyy-MM-dd` | 유저별 특정 날짜 할 일 조회       |
| **가계부 조회** |  `GET`   | `/api/tx?userId=ID&date=yyyy-MM-dd`   | 유저별 특정 날짜 수입/지출 조회   |

---

### 💾 데이터베이스 설정 (Database Setup)

**1. 스키마 및 테이블 생성**

```sql
CREATE DATABASE IF NOT EXISTS life_manager;
USE life_manager;

-- 식단 테이블 (Meal)
CREATE TABLE meal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(255) NOT NULL,
    meal_type VARCHAR(50),
    meal_date DATE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 장보기 테이블 (Cart)
CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255),
    is_bought TINYINT(1) DEFAULT 0,
    shopping_date DATE
);
```
