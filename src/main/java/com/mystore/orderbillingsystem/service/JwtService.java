package com.mystore.orderbillingsystem.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "thisIsMyVerySecretKeyForJwtAuthentication12345";

    private Key getSigningKey() {

        return new SecretKeySpec(
                SECRET_KEY.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(
        String username,
        String role) {

    return Jwts.builder()
            .subject(username)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(new Date(
                    System.currentTimeMillis()
                            + 1000 * 60 * 60))
            .signWith(getSigningKey())
            .compact();
}
    public String extractUsername(String token) {

    return Jwts.parser()
            .verifyWith((javax.crypto.SecretKey) getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
}
}