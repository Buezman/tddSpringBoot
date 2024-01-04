package com.buezman.testdemo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodoJunitTest {

    @Test
    void shouldCreateNewTodo() {
        Todo newTodo = new Todo("TEST", true);
        Assertions.assertEquals("TEST", newTodo.name(), "Todo does not exist");
    }
}
