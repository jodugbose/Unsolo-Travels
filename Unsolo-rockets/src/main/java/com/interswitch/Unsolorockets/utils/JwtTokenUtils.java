package com.interswitch.Unsolorockets.utils;

import com.interswitch.Unsolorockets.models.User;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import java.util.Date;


@AllArgsConstructor
public class JwtTokenUtils {

        private static final String JWT_SECRET = "your-secret-key";// TODO: 04/09/2023 we should hide the key , choose a secret key and hide it
        private static final long JWT_EXPIRATION_MS = 3600000;

        public static String generateToken(User user) {
            Claims claims = Jwts.claims().setSubject(user.getId().toString());
            claims.put("email", user.getEmail());
            claims.put("roles", user.getRole());

            Date now = new Date();
            Date expiration = new Date(now.getTime() + JWT_EXPIRATION_MS);

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                    .compact();
        }
    }
