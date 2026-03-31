package com.gokul.SpringBasicSecurityDemoPractice.JWTUtil;

import com.gokul.SpringBasicSecurityDemoPractice.userAuth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    public static final long EXPIRATION_DURATION = 1000 * 60 * 60;
    public static final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890!@#";
    public SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    @Autowired
    private CustomUserDetailsService userDetailsService;


    private Claims getPayload(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateJWT(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = getPayload(token);
        return claims.getSubject();
    }

    public boolean validateJWT(UserDetails userDetails, String token) {
        Claims claims = getPayload(token);

        //username verification
        String username = claims.getSubject();
        return userDetails.getUsername().equals(username) && isNotExpired(token);
    }

    private boolean isNotExpired(String token) {
        return getPayload(token).getExpiration().after(new Date());
    }


}
