package com.austral.triviagoservice.security;

import com.austral.triviagoservice.business.UserService;
import com.austral.triviagoservice.business.impl.JwtService;
import com.austral.triviagoservice.persistence.domain.User;
import com.sun.istack.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserService userService;

    public  JWTAuthenticationFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @Override
    protected void doFilterInternal(
            @NotNull javax.servlet.http.HttpServletRequest request,
            @NotNull javax.servlet.http.HttpServletResponse response,
            @NotNull javax.servlet.FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken;
        String username;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
//            throw new ServletException("An error ocurred within the authentication");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
            filterChain.doFilter(request, response);
            return;

        }

        jwtToken = authHeader.substring(7);
        username = jwtService.extractUsername(jwtToken);
        User user = this.userService.findByEmail(username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());

        if (!jwtService.isTokenValid(jwtToken,user)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            filterChain.doFilter(request, response);
            return;
        }

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            filterChain.doFilter(request, response);
            return;
        }
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth");
    }
}
