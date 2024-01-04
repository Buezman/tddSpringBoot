package com.buezman.testdemo.todo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoRepository todoRepository;

    @Test
    void shouldGetAllTodos() throws Exception {
        List<Todo> todos = List.of(new Todo("test1", true), new Todo("test2", false));
        Mockito.when(todoRepository.findAll()).thenReturn(todos);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos"))
                .andExpect(status().isOk());
    }
}