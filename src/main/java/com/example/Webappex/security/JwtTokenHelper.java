package com.example.Webappex.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper implements Serializable {
    public static final long JWT_TOKEN_VALIDITY= 12*60*60;

    @Value("${jwt.secret}")
    private String secret;

    private static final long serialVersionUID= 234234523523L;

//    retrive username

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

//    for retrieving any information from token we will need the secret key
    public <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationdateFromToken(String token){
        return getClaimsFromToken(token,Claims::getExpiration);
    }

    //    for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

//    Checking if token is expired
    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationdateFromToken(token);
        return expiration.before(new Date());
    }

//    Generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims =new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }
    private String doGenerateToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String username= getUsernameFromToken(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
