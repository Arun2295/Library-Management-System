package com.fitengineer.librarysystem.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String secretKey = "iamarunsambyaliamarunsambyalandiloveprogramming";


    public String generatetoken(String username){
    Map<String,Object> claims = new HashMap<>();

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() +1000*60*10))
            .signWith(getKey(),SignatureAlgorithm.HS256)
            .compact();



}
public Key getKey(){
    byte[] keybytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keybytes);


}
public String extractusername(String token){
    return extractClaim(token,Claims::getSubject);

}
public boolean istokenvalid(String token,UserDetails userDetails){
    final String username = extractusername(token);
    return (username.equals(userDetails.getUsername()) && !istokenExpired(token));
}
public boolean istokenExpired(String token){
    return  extractExpiration(token).before(new Date());
    
}
public Date extractExpiration(String token){
    return extractClaim(token, Claims::getExpiration);
}
public <T> T  extractClaim(String token, Function<Claims, T> claimResolver){
    final Claims claims  = extractAllClaims(token);
    return claimResolver.apply(claims);
}
private Claims extractAllClaims(String token){
    return Jwts.parserBuilder()
             .setSigningKey(getKey())
             .build()
             .parseClaimsJws(token)
             .getBody();
}



}
