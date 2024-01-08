package com.buezman.testdemo.user;

import com.buezman.testdemo.config.BaseResponse;
import com.buezman.testdemo.config.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        String raw = "dgnravepay:BasicAuthTEST9PSB558@";
        String base64 = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
        log.info(base64);
        log.info(new String(Base64.getDecoder().decode("bmVhcnBheXM6Y285OWxvZ3U4aDA0N25uYXpi")));
        log.info(raw);
    }

    @Override
    public BaseResponse<User> createUser(UserRequest userRequest) {
        validateRequest(userRequest);
        User user = mapUserRequestToUser(userRequest);
        User newUser = userRepository.save(user);
        return BaseResponse.<User>builder()
                .status("success")
                .message("New user created")
                .data(newUser)
                .build();
    }

    private void validateRequest(UserRequest userRequest) {
        if (userRequest.getAge() < 18) {
            throw new CustomException("failed", "invalid age");
        }
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            throw new CustomException("failed", "password mismatch");
        }
    }

    private User mapUserRequestToUser(UserRequest userRequest) {
        return User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .fullName(String.format("%s %s", userRequest.getFirstname(), userRequest.getLastname()))
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .password(userRequest.getPassword())
                .build();
    }
}
