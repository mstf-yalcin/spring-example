package com.spring.jwt.demo.jwt;


import com.spring.jwt.demo.dto.RefreshTokenRequestDto;
import com.spring.jwt.demo.entity.RefreshToken;
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
import java.util.function.Function;

@Service
public class JwtService {


    @Value("${security.jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${security.jwt.accessToken.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    private final RefreshTokenService refreshTokenService;

    public JwtService(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

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

    public boolean isTokenExpired(String token) { //TO DO: change dateTime to -> zonedDateTime
        return ExportToken(token, Claims::getExpiration).before(Date.from(java.time.ZonedDateTime.now().toInstant()));
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


//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) //15 minute


    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getEmail())
                .setAudience("orion")
                .setIssuer("orion.com")
                .claim("test", "test")
                .claim("test2", "test2")
                .setIssuedAt(Date.from(java.time.ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(java.time.ZonedDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRATION).toInstant()))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public RefreshToken generateRefreshToken(User user) {
        return refreshTokenService.generateRefreshToken(user);
    }

    public RefreshToken refreshTokenLogin(RefreshTokenRequestDto refreshTokenRequestDto) {
        return refreshTokenService.refreshTokenLogin(refreshTokenRequestDto);
    }

}
