package com.spring.jwt.demo.jwt;


import com.spring.jwt.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {


    @Value("${security.jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${security.jwt.accessToken.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${security.jwt.refreshToken.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public String getEmail(String token) {
        return ExportToken(token, Claims::getSubject);
    }

    public <T> T ExportToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    private Key getKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControl(String token, UserDetails userDetails) {
        final String email = getEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return ExportToken(token, Claims::getExpiration).before(new Date());
    }

    //    public boolean validateJwtToken(String authToken) {
    //        try {
    //            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    //            return true;
    //        } catch (SignatureException e) {
    //            logger.error("Invalid JWT signature: {}", e.getMessage());
    //        } catch (MalformedJwtException e) {
    //            logger.error("Invalid JWT token: {}", e.getMessage());
    //        } catch (ExpiredJwtException e) {
    //            logger.error("JWT token is expired: {}", e.getMessage());
    //        } catch (UnsupportedJwtException e) {
    //            logger.error("JWT token is unsupported: {}", e.getMessage());
    //        } catch (IllegalArgumentException e) {
    //            logger.error("JWT claims string is empty: {}", e.getMessage());
    //        }
    //
    //        return false;
    //    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getEmail())
                .setAudience("orion")
                .setIssuer("orion.com")
                .claim("test","test")
                .claim("test2","test2")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) //15 minute
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(User user) {


        return UUID.randomUUID().toString();
    }
}
