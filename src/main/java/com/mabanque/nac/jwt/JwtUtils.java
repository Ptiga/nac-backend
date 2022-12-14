package com.mabanque.nac.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    //Validité du token (5h -> 5*60min*60sec)
    long JWT_VALIDITY = 5 * 60 * 60;

    private static final String AUTHORITIES_KEY = "sub";

    private static final String ROLE_KEY = "auth";

    //Clé secrète
    @Value("${jwt.secret}")
    String secretKey;

    //Méthode de génération du token qui sera appelée lorsqu'on voudra s'authentifier (ou s'inscrire s'il y a un endpoint d'inscription)
    public String generateToken(Authentication authentication) {
        //Méthode issue de la doc JWT pour générer un token
        //TODO: Voir si ça ne serait pas ici que le login remonte en tant que rôle
        Map<String, Object> claims = new HashMap<>();
        //Ajout de l'authorité dans le map
        //claims.put(ROLE_KEY, authentication.getAuthorities());
        //.setSubjet est passé avec le GetName.

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                //.setClaims(claims)
                .claim(ROLE_KEY, authorities)
                .setSubject(authentication.getName())//login de l'utilisateur concerné
                //Essayer avec :
                //.setID(authentication.getName())
                //.setSubject(authentication.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))//Début de validité du token
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000))//Date de fin de validité du token (valeur de la constante JWT_VALIDITY x1000 pour convertir en millisecondes)
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();//Protocole de signature (avec notre clé secrète)
    }

    //Méthode pour vérifier que le token est en cours de validité et contient un utilisateur en BDD (quand on cherche à appeler un endpoint)
    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();//On initialise les claims JWT avec la clé secrète

        Collection<? extends GrantedAuthority> authorities = Arrays
                //.stream(claims.get(AUTHORITIES_KEY).toString().split(","))//la constante AUTHORITIES_KEY va nous permettre de récupérr l'utilisateur à partir du token
                .stream(claims.get(ROLE_KEY).toString().split(","))//la constante ROLE_KEY va nous permettre de récupérr le rôle de l'utilisateur à partir du token
                //.stream(claims, claims.get(AUTHORITIES_KEY).toString().split(","),claims.get(ROLE_KEY).toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //On créé un utilisateur Spring Security
        //Attention : on importe la classe User de SpringSecurity, pas celle du projet !!!
        //TODO: Voir la valeur de 'authorities'
        User principal = new User(claims.getSubject(), "", authorities);



        //Méthode d'authentification Spring Security qui va appeller la méthode dans MyUserDetailService
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    }

}
