package com.learn.project.journal.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    public String generateToken(@NonNull String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);

    }

    private String createToken(Map<String, Object> claims, @NonNull String userName) {
        return Jwts.builder()
                .setClaims(claims)                      // custom claims (can be empty)
                .setSubject(userName)                   // required: the "sub" (user id or username)
                .setHeaderParam("typ", "jwt")
                .setIssuedAt(new Date(System.currentTimeMillis())) // when the token was created
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // secret key & algo
                .compact();                             // generate the token string
    }

    private Key getSigningKey() {
        String base64Secret = "YVdoZ1RtQmxnZ01hdWxQSkpPU2NSRkFkY2d1dHR5QzQ=";
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        return Keys.hmacShaKeyFor(keyBytes); // using io.jsonwebtoken.security.Keys
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // use setSigningKey for HMAC or RSA keys
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
