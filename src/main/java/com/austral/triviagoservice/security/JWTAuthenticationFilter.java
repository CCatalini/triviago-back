package com.austral.triviagoservice.security;

import com.austral.triviagoservice.persistance.domain.User;
import com.sun.istack.NotNull;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            throw new ServletException("An error ocurred within the authentication");
        }

        jwtToken = authHeader.substring(7);
        username = jwtService.extractUsername(jwtToken);
        User user = this.userService.findByEmail(username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null);

        if (!jwtService.isTokenValid(jwtToken,user)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
