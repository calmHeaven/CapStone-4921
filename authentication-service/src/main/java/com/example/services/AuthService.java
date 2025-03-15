package com.example.services;

import com.example.model.AuthRequest;
import com.example.model.AuthResponse;
import com.example.model.UserVO;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;


    public AuthResponse signUp(AuthRequest request) {
        //validate if user exists in db yet or not
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
            UserVO signedUpUser = restTemplate.postForObject("https://user-service/users", request, UserVO.class);

        assert signedUpUser != null;
        String accessToken = jwtUtil.generate(signedUpUser.getId(), signedUpUser.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(signedUpUser.getId(), signedUpUser.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
