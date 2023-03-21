package com.example.springboots.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;



@Service
public class JwtServices {
    private static final String SECRET_KEY = "jWnZr4u7x!A%D*G-KaPdSgVkYp2s5v8y";
public static String extractUserName(String token){

    return extractClaims(token, Claims::getSubject);
}
public static <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
    final Claims claims = extractAllclaim(token);
    return claimsResolver.apply(claims);
}
public String generatedToken(UserDetails userDetails){
return generatedToken(new HashMap<>(), userDetails);
}
public String generatedToken(
    Map<String, Object> extraClaims,
            UserDetails userDetails){
    return Jwts.builder().setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
}
public static boolean isTokenValid(String token, UserDetails userDetails){
   final String userName = extractUserName(token);
   return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
}

    private static boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private static Claims extractAllclaim(String token){

    return Jwts.parserBuilder().setSigningKey(getSignInKey())
            .build().parseClaimsJws(token)
            .getBody();
}

    private static Key getSignInKey() {
    byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
