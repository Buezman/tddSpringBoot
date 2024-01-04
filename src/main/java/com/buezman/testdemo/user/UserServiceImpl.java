package com.buezman.testdemo.user;

import com.buezman.testdemo.config.BaseResponse;
import com.buezman.testdemo.config.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
