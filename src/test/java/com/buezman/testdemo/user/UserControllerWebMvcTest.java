package com.buezman.testdemo.user;

import com.buezman.testdemo.config.BaseResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Slf4j
class UserControllerWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Gson gson;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    UserRequest userRequest = UserRequest.builder()
            .firstname("Test")
            .lastname("User")
            .age(21)
            .email("testuser@gmail.com")
            .password("testpass")
            .confirmPassword("testpass")
            .build();

    User user = User.builder()
            .id(1L)
            .firstname("Daniel")
            .lastname("Glover")
            .email("dannyD@gmail.com")
            .age(21)
            .password("Danny@123")
            .fullName("Daniel Glover")
            .build();

    @Test
    void givenValidRequestShouldCreateNewUser() throws Exception {
        Mockito.when(userService.createUser(userRequest)).thenReturn(new BaseResponse<>("success", "New User created", user));
//        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        String response = """
                {
                    "status": "success",
                    "message": "New user created",
                    "data": {
                        "id": 1,
                        "fullName": "Test User",
                        "firstname": "Test",
                        "lastname": "User",
                        "email": "testuser@gmail.com",
                        "age": 21,
                        "password": "testpass"
                    }
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(userRequest))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
//                .andExpect(content().json(response));
//                .andExpect(jsonPath("$.status").value("success"))
//                .andExpect(jsonPath("$.data.fullName").value("Test User"));

    }
}
