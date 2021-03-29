package com.example.demo.security;

/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.domain.entity.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.springframework.com.example.demo.security.authentication.AuthenticationManager;
import org.springframework.com.example.demo.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.com.example.demo.security.core.Authentication;
import org.springframework.com.example.demo.security.core.AuthenticationException;
import org.springframework.com.example.demo.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JWTAuthenticationFilter_eski extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter_eski(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Member creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Member.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants_eski.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants_eski.SECRET.getBytes())
                .compact();
        res.addHeader(SecurityConstants_eski.HEADER_STRING, SecurityConstants_eski.TOKEN_PREFIX + token);
    }

}
*/
