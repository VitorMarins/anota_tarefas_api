package com.vitorbm.anota_tarefas_api.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtTokenService {
    private final String SECRET_STRING = "928e81874db1e78a2dae3e3ee612bbdd249b277dfd5e0b367e44a98df6f3110f440f2c20ee75f01a7c04795a6f28493415f1d7156e33108ca0c99dd7c8c32fd0";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 600000)) // 10 minutos
                .signWith(SECRET_KEY) // Agora o objeto SecretKey é aceito aqui
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}