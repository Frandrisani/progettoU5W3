package francescoandrisani.progettoU5W3.security;

import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.exceptions.Unauthorized;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(User user){
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+1000*60*60*24*7)).subject(String.valueOf(user.getId())).signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
    }

    public void verifyToken(String token){
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        }catch (Exception ex){
            throw new Unauthorized("!ATTENZIONE! Problemi con il token! Rieffettua il login");
        }
    }

}
