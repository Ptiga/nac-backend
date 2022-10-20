package com.socgen.nac.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
//Cette classe va servir de filtre pour savoir si le service appellé peut être consulté ou non.
//le 'OncePerRequestFilter' signifie que le filtre va être appelé pour chaque url.

    @Autowired
    JwtUtils jwtUtils;

    public static final String AUTHORIZATION_HEADER = "Authorization";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //On récupère le token dans la requête
        String jwt = resolveToken(request);
        if(StringUtils.hasText(jwt)){
            //A partir du token, on appelle la méthode pour authentifier l'utilisateur
            Authentication authentication = jwtUtils.getAuthentication(jwt);
            //Un fois effectué, on enregistre l'utilisateur en base
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


    }

    private String resolveToken(HttpServletRequest request) {
        //Token qui se trouve dans l'en-tête (header) de la requête
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        //Si notre chaîne de caractère contient du texte et si elle commence par 'Bearer ' alors on récupères la chaîne de caractères à partir du 8ème élément de la chaîne
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
