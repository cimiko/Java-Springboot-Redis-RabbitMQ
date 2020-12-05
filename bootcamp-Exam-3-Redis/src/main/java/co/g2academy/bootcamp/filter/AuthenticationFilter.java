/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.filter;

import co.g2academy.bootcamp.model.Login;
import static co.g2academy.bootcamp.model.SecurityConstants.EXPIRATION_TIME;
import static co.g2academy.bootcamp.model.SecurityConstants.KEY;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author cimiko
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Login login
                    = new ObjectMapper().readValue(request.getInputStream(), Login.class);

            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(login.getUserName(),
                            login.getPassword(), Collections.emptyList());
            return authenticationManager.authenticate(token);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(KEY.getBytes());
        User user = (User) authResult.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        //create token
        String token = Jwts.builder().setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(exp).compact();
        response.setHeader("token", token);
    }

}
