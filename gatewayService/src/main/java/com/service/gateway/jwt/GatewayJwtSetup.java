package com.service.gateway.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// import com.service.gateway.dto.LoginRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class GatewayJwtSetup {

    @Value("${jwt.secret}")
    private String secret;

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }



    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
