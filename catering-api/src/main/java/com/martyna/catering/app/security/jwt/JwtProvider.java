package com.martyna.catering.app.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import com.martyna.catering.app.security.service.UserPrinciple;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Value("${catering.app.jwtSecret}")
    private String jwtSecret;

    @Value("${catering.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication){

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrinciple.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("invalid signature");
        } catch (MalformedJwtException ex) {
            logger.error("invalid jwt token");
        } catch (ExpiredJwtException ex) {
            logger.error("expired token");
        } catch  (UnsupportedJwtException ex) {
            logger.error("unsupported jwt token");
        } catch (IllegalArgumentException ex) {
            logger.error("emmpty string");
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
