package com.buezman.testdemo.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoAsserJTest {

    @Test
    void shouldCreateNewTodo() {
        Todo todo = new Todo("TEST", true);
        assertThat(todo.name())
                .isEqualToIgnoringCase("test");
    }
}
