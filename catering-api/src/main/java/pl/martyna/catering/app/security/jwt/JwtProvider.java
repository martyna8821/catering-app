package pl.martyna.catering.app.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import pl.martyna.catering.app.security.service.UserPrinciple;

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
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

     boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid signature: "+ ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid jwt token: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired token");
        } catch  (UnsupportedJwtException ex) {
            logger.error("Unsupported jwt token: "+ ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error( ex.getMessage());
        }
        return false;
    }

    String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
