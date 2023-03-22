package com.project.haiduk.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.haiduk.service.impl.TicketServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    public String SECRET_KEY;

    @Value("${jwt.sessionTime}")
    private int sessionTime;

    private static final Logger log = Logger.getLogger(JWTUtil.class);
    public String generateToken(String username){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(sessionTime).toInstant());
        log.info(String.format("Create new JWT for user: %s ", username));

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("HelpDeskServer")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withSubject("User details")
                .withIssuer("HelpDeskServer")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        log.info(String.format("Validate JWT for user: %s ", jwt.getClaim("username").asString()));
        return jwt.getClaim("username").asString();
    }
}
