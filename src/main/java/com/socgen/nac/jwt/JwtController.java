package com.socgen.nac.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.socgen.nac.jwt.JwtFilter.AUTHORIZATION_HEADER;

@RestController
public class JwtController {

    @Autowired
    JwtUtils jwtUtils;

    //Pour nous permettre de gérer l'autentification
    @Autowired
    AuthenticationManagerBuilder autenticationManagerBuilder;

    @PostMapping("/autenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response){
        //On va voir si l'utilisateur éxiste en base
        Authentication authentication = logUser(jwtRequest.getLogin(), jwtRequest.getPassword());

        //Une fois authentifié (côté Spring Security), on va pouvoir générer le jeton (Token) de l'utilisateur
        String jwt = jwtUtils.generateToken(authentication); //Token qui sera à transmettre au front (client)

        //On met le token dans le header (via la notion 'Bearer')
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "bearer " + jwt);
        httpHeaders.add(AUTHORIZATION_HEADER, "bearer " + jwt);

        //On récupère l'utilisateur connecté
        Object principal = authentication.getPrincipal();

        return new ResponseEntity<>(new JwtResponse(principal.toString()), httpHeaders, HttpStatus.OK);
    }

    public Authentication logUser(String login, String password){
        //Via l'authenticateManagerBuilder, on appelle la méthode autheticate pour s'identifier avec login + mdp
        Authentication authentication = autenticationManagerBuilder.getObject().authenticate((new UsernamePasswordAuthenticationToken(login, password)));

        //Une fois connecté, on défini l'autentification (ça va appeler la méthode 'loadUserByUsername' créée dans MyUserDetailService)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
}
