package ru.itmo.lostandfound.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String accessTokenSecret;

    @Value("${token.expire-time}")
    private Long accessTokenExpirationMs;

    private Key signingKey;

    @PostConstruct
    private void initSigningKey() {
        byte[] keyDecoded = Decoders.BASE64.decode(accessTokenSecret);
        signingKey = Keys.hmacShaKeyFor(keyDecoded);
    }

    public String generateAccessToken(String username) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + accessTokenExpirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean checkToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(signingKey).build();
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            long expirationTime = claims.getExpiration().getTime();
            long currentTime = System.currentTimeMillis();
            return currentTime <= expirationTime;
        } catch (Exception e) {
            return false;
        }
    }

    public String usernameFromToken(String token){
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(signingKey).build();
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }
}
