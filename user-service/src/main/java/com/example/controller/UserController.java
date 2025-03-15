package com.example.controller;

import com.example.model.UserVO;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
        private final UserService userService;
@PostMapping
    public ResponseEntity<UserVO> saveUser(@RequestBody UserVO user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
}
