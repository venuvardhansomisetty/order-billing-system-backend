package com.mystore.orderbillingsystem.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mystore.orderbillingsystem.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {


                String path = request.getServletPath();

    if (path.equals("/users/login")
            || path.equals("/users/register")) {

        filterChain.doFilter(request, response);
        return;
    }

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader != null
                && authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);
String username =
        jwtService.extractUsername(token);

UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(
                username,
                null,
                List.of(
    new SimpleGrantedAuthority("ROLE_ADMIN")));

SecurityContextHolder.getContext()
        .setAuthentication(authToken);
        }

        filterChain.doFilter(
                request,
                response);
    }
}