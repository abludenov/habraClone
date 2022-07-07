package com.javamentor.backend.security.jwt;

import com.javamentor.backend.service.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtills jwtUtility;
    private final JwtUserDetailsServiceImpl userDetailsService;

    public JwtFilter(JwtUtills jwtUtility, JwtUserDetailsServiceImpl myUserDetailsService) {
        this.jwtUtility = jwtUtility;
        this.userDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username;
        String jwtToken;

        /**
         * Логика фильтрации
         * Токен пользователя передаётся через Header, всегда содержит в себе приставку Bearer(с пробелом).
         * В целях получения только лишь токена мы избавляемся от не нужной приставки Bearer.
         * Из токена получаем username, а так же проверяем аутентифицирован ли данный пользователь.
         * Если токен проходит валидацию, то вносим пользователя в контекст через jwtUserDetails
         * В случае когда токен отсутствует или же в случае неудачи валидации токена
         * Пользователь вносится в контекст как гость с ролью GUEST.
         *
         */
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            username = jwtUtility.getUsernameFromToken(jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtility.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    guestAuth();
                }
            }
        } else {
            guestAuth();
        }
        filterChain.doFilter(request, response);
    }

    private void guestAuth() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("GUEST", null,
                AuthorityUtils.createAuthorityList("ROLE_GUEST"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}