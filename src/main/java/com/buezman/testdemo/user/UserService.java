package com.buezman.testdemo.user;

import com.buezman.testdemo.config.BaseResponse;

public interface UserService {
    BaseResponse<User> createUser(UserRequest userRequest);
}
