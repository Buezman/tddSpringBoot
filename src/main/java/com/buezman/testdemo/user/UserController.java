package com.buezman.testdemo.user;

import com.buezman.testdemo.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public BaseResponse<User> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
