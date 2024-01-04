package com.buezman.testdemo.user;

import com.buezman.testdemo.config.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Slf4j
class UserServiceTest {

    UserRequest userRequest = UserRequest.builder()
            .firstname("Daniel")
            .lastname("Glover")
            .email("dannyD@gmail.com")
            .age(21)
            .password("Danny@123")
            .confirmPassword("Danny@123")
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

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("TEST FOR AGE < 18 SHOULD FAIL WITH 'invalid age' MESSAGE")
    void givenAgeLessThan18_should_fail_with_invalid_age() {
        userRequest.setAge(16);
        assertThatThrownBy(() -> userService.createUser(userRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("invalid age");
    }

    @Test
    @DisplayName("TEST FOR PASSWORD MISMATCH SHOULD FAIL WITH 'password mismatch' MESSAGE")
    void givenAPasswordMismatch_should_fail_with_passwordMismatch() {
        userRequest.setConfirmPassword("Danny@1234");
        assertThatThrownBy(() -> userService.createUser(userRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("password mismatch");
    }

    @Test
    void givenValidUserRequest_shouldCreateUserSuccessfully() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        BaseResponse<User> response = userService.createUser(userRequest);
        assertNotNull(response);
        assertThat(response.getData().getFullName()).isEqualToIgnoringCase("Daniel Glover");
    }
}