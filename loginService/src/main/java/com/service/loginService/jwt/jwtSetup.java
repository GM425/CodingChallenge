package com.service.loginService.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.service.loginService.dto.LoginRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
// import org.springframework.security.crypto.codec.Decoders;

import io.jsonwebtoken.security.Keys;

@Service
public class jwtSetup {

    @Value("${jwt.secret}")
    private String secret;


    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public String generateToken(LoginRequest loginRequest){
        String jwtToken="";
        jwtToken = Jwts.builder()
        .setSubject(loginRequest.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000 * 2))
        .signWith(getSignKey(), SignatureAlgorithm.HS256)
        .compact();
        return jwtToken;
    }

    private Key getSignKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}


