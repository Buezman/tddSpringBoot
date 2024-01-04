package com.buezman.testdemo.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private String password;
    private String confirmPassword;
}
