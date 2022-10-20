package com.socgen.nac.configuration;

import com.socgen.nac.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    RestAutentificationEntryPoint restAutentificationEntryPoint;

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http
            .csrf().disable()//désactivé car faille de sécurité assez connue (?)
            .exceptionHandling()//Méthode pour demander à Spring de gérer les exceptions
            .authenticationEntryPoint((restAutentificationEntryPoint))//Pour renvoyer une erreur 401 au lieu d'un formulaire de login (après avoir créé classe RestAutentificationEntryPoint)
            .and()//Pour ajouter d'autre comportements
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Gestion de session: on dit à Spring qu'on utilise une politique qui n'a pas de session côté serveur (STATELESS) -> On va passer par un JWT donc le stockage se fera côté client (Front).
            .and()
            .authorizeRequests()//Pour autoriser certaines routes
            .antMatchers("/users").permitAll()//Autoriser cette url sans avoir besoin de s'authentifier
            .antMatchers("/create-users").permitAll()//Autoriser cette url sans avoir besoin de s'authentifier
            .anyRequest().authenticated();//Toutes les autres requeêtes devont être authentifiées

        //Avant d'effectuer l'authentification, on vérifie le token (filtre jwtFiler)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
