package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService
{
     public static final String SECRET= "68576D5A7134743777217A25432A462D4A614E635266556A586E327235753878";

     public String extractUsername(String token){
         return extractClaim(token, Claims:: getSubject);

     }
     // we have to trake the generic
     public <T>  T extractClaim(String token , Function<Claims,T> claimResolver){
             final Claims claims =extractAllClaims(token);
             return claimResolver.apply(claims);


     }
     public Date extractExpiration (String token){
         return extractClaim(token , Claims::getExpiration);
     }
     public Boolean isTokenExpired(String token){
         return extractExpiration(token).before(new Date());

     }
     public Boolean validateToken(String token , UserDetails userDetails){
         final String username =extractUsername(token);
         return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));

     }

     /// till now we only check validate the token ...now we will make create token

     private String createToken(Map<String ,Object> claims , String username ){
         return Jwts.builder()
                 .setClaims(claims)
                 .setSubject(username)
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis() + 1000 *60*1))
                 .signWith(getSignKey() , SignatureAlgorithm.ES256) .compact();


     }


     public Claims extractAllClaims(String token){
         return Jwts
                 .parser()
                 .setSigningKey(getSignKey())
                 .build() // its just giving thye builder of that class
                 .parseClaimsJws(token)
                 .getBody();



         }
    private Key getSignKey(){
         byte[] KeyBytes = Decoders.BASE64.decode(SECRET);
         return Keys.hmacShaKeyFor(KeyBytes);

     }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }



}
