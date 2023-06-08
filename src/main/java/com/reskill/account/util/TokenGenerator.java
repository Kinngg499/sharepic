package com.reskill.account.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenGenerator implements Serializable {

    private static final String SIGNING_KEY = "sharepicusecasestudyyforgooglecloudpractioner";

    public String generateToken(String subject, String clientId, long expiryTimeInSec) {
        Date expiration = null;
        expiration = new Date(System.currentTimeMillis() + expiryTimeInSec);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", clientId);

        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .signWith(getSigningKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SIGNING_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
