package com.mysite.backend.controller;

import com.mysite.backend.entity.Todo;
import com.mysite.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
@CrossOrigin(origins = "*")
public class TodoController {
    private final TodoRepository repository;

    @GetMapping
    public List<Todo> getList(@RequestParam String userId, @RequestParam LocalDate date) {
        return repository.findAllByUserIdAndDoDate(userId, date);
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return repository.save(todo);
    }
}