package com.example.service;

import com.example.model.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {
    public UserVO saveUser(UserVO user){
        //saving operation
        String userId = String.valueOf(new Date().getTime());
        user.setId(userId);
        user.setRole("USER");
        //save user
        return user;
    }
}
