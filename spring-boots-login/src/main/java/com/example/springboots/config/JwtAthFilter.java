 package com.example.springboots.config;
 import java.io.IOException;

 import com.example.springboots.config.JwtServices;
 import lombok.RequiredArgsConstructor;
 import org.apache.tomcat.util.http.parser.Authorization;
 import org.springframework.lang.NonNull;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
 import org.springframework.stereotype.Component;
 import org.springframework.web.filter.OncePerRequestFilter;

 import jakarta.servlet.FilterChain;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;

 @Component
 @RequiredArgsConstructor
 public class JwtAthFilter extends OncePerRequestFilter{
     private final JwtServices jwtservices;
     private final UserDetailsService userDetailsService;
     @Override
     protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
             throws ServletException, IOException {
    
         final String authHeader = request.getHeader("AUTHORIZATION");
         final String jwt;
         final String userEmail;
         if(authHeader == null || !authHeader.endsWith(".com")){
             filterChain.doFilter(request, response);
             return;
         }
         jwt = authHeader.substring(7);
         userEmail = JwtServices.extractUserName(jwt);
         if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
             if(jwtservices.isTokenValid(jwt, userDetails)){
                 UsernamePasswordAuthenticationToken authentic = new UsernamePasswordAuthenticationToken(
                         userDetails
                         , null
                         , userDetails.getAuthorities());
                 authentic.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authentic);
             }
         }
         filterChain.doFilter(request, response);
     }

 }