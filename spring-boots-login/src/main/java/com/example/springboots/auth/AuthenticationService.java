package com.example.springboots.auth;



import com.example.springboots.config.JwtServices;
import com.example.springboots.user.Role;
import com.example.springboots.user.User;
import com.example.springboots.user.UserRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
private final UserRepo userRepo;
private final PasswordEncoder passwordEncoder;
private final JwtServices jwtService;
private final AuthenticationManager authenticationManager;


    public AuthenticateRespone register(RegisterRequest request) {
        var user = User.builder()
        .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.User)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generatedToken(user);
        return AuthenticateRespone.builder().token(jwtToken).build();

    }

    public AuthenticateRespone authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPassWord()
                        , request.getEmail()));
        var user =userRepo.findByEmail(request.getEmail()).orElseThrow();
         userRepo.save(user);
        var jwtToken = jwtService.generatedToken(user);
        return AuthenticateRespone.builder()
                .token(jwtToken)
                .build();


    }
}
