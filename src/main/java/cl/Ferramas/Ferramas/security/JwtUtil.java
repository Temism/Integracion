package cl.Ferramas.Ferramas.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "ferramas123456789012345678901234567890";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateTokenConRol(String email, String rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("rol", rol.toUpperCase())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
