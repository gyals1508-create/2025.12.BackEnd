package com.mysite.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// User.java
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "USRID")
    private String userId;
    @Column(name = "USRNM") private String userName;
    @Column(name = "PASSWD") private String password;
    @Column(name = "EMAIL") private String email;
}

// UserRepository.java / UserController.java (생략 가능하나 틀은 동일함)