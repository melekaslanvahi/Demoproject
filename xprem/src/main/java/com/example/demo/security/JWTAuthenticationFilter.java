package com.example.demo.security;

/*
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.com.example.demo.security.authentication.AuthenticationManager;
import org.springframework.com.example.demo.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.com.example.demo.security.core.Authentication;
import org.springframework.com.example.demo.security.core.AuthenticationException;
import org.springframework.com.example.demo.security.core.userdetails.User;
import org.springframework.com.example.demo.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            com.example.demo.domain.entity.User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), com.example.demo.domain.entity.User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        Date expirationDate = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withClaim("role", ((User) auth.getPrincipal()).getAuthorities().iterator().next().getAuthority())
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        res.setHeader("Content-Type", "application/json");
        res.getWriter().write("{\"token\":\"JWT " + token + "\", \"expires\": \"" + expirationDate.toString() + "\"}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
*/