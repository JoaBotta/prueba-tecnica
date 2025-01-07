package com.joa.springboot.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.joa.springboot.Usuario.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    /**
     * Genera un token JWT con claims adicionales.
     * @param extraClaims Claims adicionales que se incluirán en el token.
     * @param user El usuario cuyos detalles se utilizarán para generar el token.
     * @return Token JWT generado.
     */
    public String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(((Usuario) user).getEmail()) // Usamos el email como subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira en 10 horas
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Genera un token JWT sin claims adicionales.
     * @param user El usuario cuyos detalles se utilizarán para generar el token.
     * @return Token JWT generado.
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Obtiene el email (subject) del token JWT.
     * @param token El token JWT.
     * @return El email extraído del token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si un token es válido comparando el username y la expiración.
     * @param token El token JWT.
     * @param userDetails Los detalles del usuario.
     * @return True si el token es válido, false en caso contrario.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Obtiene un claim específico del token.
     * @param token El token JWT.
     * @param claimsResolver La función para resolver el claim.
     * @return El claim solicitado.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Verifica si el token ha expirado.
     * @param token El token JWT.
     * @return True si el token ha expirado, false en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * Obtiene la fecha de expiración del token.
     * @param token El token JWT.
     * @return La fecha de expiración.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Obtiene todos los claims del token.
     * @param token El token JWT.
     * @return Los claims del token.
     */
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtiene la clave secreta para firmar los tokens.
     * @return La clave secreta.
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
