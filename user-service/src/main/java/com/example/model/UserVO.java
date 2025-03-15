package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVO {
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;
}
