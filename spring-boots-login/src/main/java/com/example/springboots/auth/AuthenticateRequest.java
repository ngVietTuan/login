package com.example.springboots.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.constant.Constable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {

    private String email;
    private String passWord;

    public String getEmail(String email) {
        return email;
    }
}
