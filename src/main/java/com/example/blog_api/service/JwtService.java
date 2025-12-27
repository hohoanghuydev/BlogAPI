package com.example.blog_api.service;

import com.example.blog_api.dto.UserResponseDto;
import com.example.blog_api.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "this-is-my-super-secret-key-32-bytes-long";
    private final int EXPIRATION_TIME_OF_A_DAY = 1000 * 60 * 60 * 24;

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();//Data ở đây, đối tượng ở đây

        String token = Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .and()
                .signWith(getKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_OF_A_DAY))
                .compact();

        return token;
    }

    private SecretKey getKey() {
//        byte[] encoded = Decoders.BASE64.decode(
//                Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())
//        ); có phải do nó không
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractContent(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Parse token get data
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractContent(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
