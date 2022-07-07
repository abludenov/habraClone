package com.javamentor.backend.security.jwt;

import com.javamentor.backend.model.dto.UserLoginDto;
import com.javamentor.backend.service.JwtUserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtills implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    /**
     * JWT_TOKEN_VALIDITY время жизни токена, задаётся в миллисекундах
     */
    public static final long JWT_TOKEN_VALIDITY = 3_600_000 * 24 * 7;
    /**
     * Установка секретного слова (может быть любым, чем сложнее, тем лучше)
     */
    private final String secret = "secret";

    private final JwtUserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    public JwtUtills(JwtUserDetailsServiceImpl userDetailsService, @Lazy AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Метод, который при успешной авторизации пользователя создаёт для него токен
     * @param loginDto
     * @return jwtToken
     * @throws AuthenticationException - обрабатывается в контроллере.
     */
    public String validate(UserLoginDto loginDto) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        return createToken(loginDto.getUsername());
    }

    /**
     * Метод создания токена на основании User'a, который тянется из БД по его username
     * Claims - наполнение токена нужной нам информацией, в нашем случае это username и его роль
     * Получение текущей даты (Unix время) и добавление к ней времени жизни токена
     *
     * @return возвращает готовый токен с указанным типом кодировки и секретным словом
     */
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        claims.put("roles", userDetails.getAuthorities());
        Date now = new Date();
        Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)//
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}