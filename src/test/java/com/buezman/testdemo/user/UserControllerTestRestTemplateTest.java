package com.buezman.testdemo.user;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTestRestTemplateTest {

    UserRequest userRequest = UserRequest.builder()
            .firstname("Test")
            .lastname("User")
            .age(21)
            .email("testuser@gmail.com")
            .password("testpass")
            .confirmPassword("testpass")
            .build();
    @Autowired
    private UserRepository userRepository;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testInvalidAge() throws JSONException {
        userRequest.setAge(16);
        String expectedJson = """
                {
                    "status": "failed",
                    "message": "invalid age"
                }
                """;
        String response = restTemplate.postForObject("http://localhost:" + port + "/api/users", userRequest, String.class);
        assertThat(response).isNotNull();
        JSONAssert.assertEquals(expectedJson, response, false);
    }

    @Test
    void testPasswordMismatch() throws JSONException {
        userRequest.setConfirmPassword("danny1@1234");
        String expectedJson = """
                {
                    "status": "failed",
                    "message": "password mismatch"
                }
                """;
        String response = restTemplate.postForObject("http://localhost:" + port + "/api/users", userRequest, String.class);
        assertThat(response).isNotNull();
        JSONAssert.assertEquals(expectedJson, response, false);
    }

    @Test
    void testCreateUser() throws JSONException {
        String expectedJson = """
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
        String response = restTemplate.postForObject("http://localhost:" + port + "/api/users", userRequest, String.class);
        assertThat(response).isNotNull();
        JSONAssert.assertEquals(expectedJson, response, false);
    }
}
