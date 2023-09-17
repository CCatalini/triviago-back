package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.persistence.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${secret.key}")
    private String secret;

    public Boolean isTokenValid (String token, User user) {
        final String username = extractUsername(token);
        return (username.equals( user.getEmail() ) && !isTokenExpired(token)) ;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("birthDate", user.getBirthDate().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject( user.getEmail() )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims extractAlClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAlClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return new JwtService().extractClaim(token, Claims::getSubject);
    }

}
