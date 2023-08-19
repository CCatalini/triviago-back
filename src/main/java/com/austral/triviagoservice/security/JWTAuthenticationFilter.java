package com.austral.triviagoservice.security;

import com.sun.istack.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jswtService;
    private UserDetailsService userDetailsService;

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
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.substring(7);
        username = jswtService.extractUsername(jwtToken);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = this.userDetailsService.loadByUSername(username);
        }
    }
}
