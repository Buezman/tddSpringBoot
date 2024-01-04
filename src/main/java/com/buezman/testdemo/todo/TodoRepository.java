package com.buezman.testdemo.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    List<Todo> todos;

    public TodoRepository() {
        todos = List.of(new Todo("test1", true), new Todo("test2", false));
    }

    public List<Todo> findAll() {
        return todos;
    }
}
