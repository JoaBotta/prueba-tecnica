package com.joa.springboot.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import lombok.RequiredArgsConstructor;

import com.joa.springboot.Usuario.UserDetailServiceImpl;


@RequiredArgsConstructor

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    final String token = getTokenFromRequest(request);
    if (token == null || !jwtService.validateToken(token)) {
        // Si no hay token o no es válido, continúa sin establecer el contexto
        filterChain.doFilter(request, response);
        return;
    }

    final String username = jwtService.getUsernameFromToken(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

        if (jwtService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    filterChain.doFilter(request, response);
}
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            logger.info("Token extraído: " + token);
            return token;
        }
        logger.warn("Token ausente o malformado");
        return null;
    }


}