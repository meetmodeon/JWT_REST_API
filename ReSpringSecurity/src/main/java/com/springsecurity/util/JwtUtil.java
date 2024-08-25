package com.springsecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    private final String SECRET_KEY="5999B03444B095D12E8C6BDB75946B4C8220BB56B21404F14CD230F66E78F8D6";

    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        Map<String,Object> header=new HashMap<>();
        header.put("type","JWT");
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userName)
                .setHeader(header)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSinginKey())
                .compact();
    }
    private SecretKey getSinginKey(){
        byte[] decodedKey= Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }
    public String extractUsername(String jwt){
        Claims claims=getString(jwt);
        return claims.getSubject();
    }

    private Claims getString(String jwt){
        return Jwts.parserBuilder()
                .setSigningKey(getSinginKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
    public boolean isTokenValid(String jwt){
        Claims claims=getString(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
