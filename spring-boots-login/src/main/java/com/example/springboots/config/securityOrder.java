//package com.example.springboots.login;
//
//import org.hibernate.jdbc.Expectations;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@EnableWebSecurity
//public class securityOrder {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//        .authorizeHttpRequests()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .httpBasic();
//
//        return http.build();
//    }
//}
