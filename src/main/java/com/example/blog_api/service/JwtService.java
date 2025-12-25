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
    private final String SECRET_KEY;

    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            SECRET_KEY = Base64.getEncoder().encodeToString(sk.getEncoded());
            System.out.println("SECRET_KEY: " + SECRET_KEY);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        String token = Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .and()
                .signWith(getKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600 * 24))
                .compact();

        return token;
    }

    private SecretKey getKey() {
        byte[] encoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(encoded);
    }

    public String extractContent(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String content, UserDetails userDetails) {
        final String userName = extractContent(content);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(content);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
