package com.gokul.SpringBasicSecurityDemoPractice.filter;

import com.gokul.SpringBasicSecurityDemoPractice.JWTUtil.JWTUtil;
import com.gokul.SpringBasicSecurityDemoPractice.userAuth.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //extracting authorization header from request
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        UserDetails userDetails = null;

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return;
        }

        token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateJWT(userDetails,token)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
                filterChain.doFilter(request,response);
            }
        }

    }
}
