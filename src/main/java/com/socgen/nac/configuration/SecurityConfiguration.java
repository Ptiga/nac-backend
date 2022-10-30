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
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    JwtFilter jwtFilter;

    //Règles de sécurité
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http
            .csrf().disable()//désactivé car faille de sécurité assez connue (?)
            .exceptionHandling()//Méthode pour demander à Spring de gérer les exceptions
            .authenticationEntryPoint(restAuthenticationEntryPoint)//Pour renvoyer une erreur 401 au lieu d'un formulaire de login (après avoir créé classe RestAutentificationEntryPoint)
            .and()//Pour ajouter d'autre comportements
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Gestion de session: on dit à Spring qu'on utilise une politique qui n'a pas de session côté serveur (STATELESS) -> On va passer par un JWT donc le stockage se fera côté client (Front).
            .and()
            .authorizeRequests()//Pour autoriser certaines routes
            .antMatchers("/").permitAll()
            .antMatchers("/static/**/").permitAll()
            .antMatchers("/users").permitAll()//Autoriser cette url sans avoir besoin de s'authentifier
            .antMatchers("/create-user").permitAll()//Autoriser cette url sans avoir besoin de s'authentifier
            .antMatchers("/create-users").permitAll()
            .antMatchers("/add-user").permitAll()
            .antMatchers("/authenticate").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/login/**").permitAll()
            .antMatchers("/Login").permitAll()
            .antMatchers("/Login/**").permitAll()
            .antMatchers("/results").permitAll()
            .antMatchers("/isConnected").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
                //TODO: Tester avec des accès spécifiques (".hasRole()")
            .antMatchers("/webjars/**").permitAll()// -> possibilité de mettre .hasRole() pour déterminer l'accès à un rôle spécifique -> .hasRole("ROLE_USER")
            .anyRequest().authenticated();//Toutes les autres requeêtes devont être authentifiées
        //Avant d'effectuer l'authentification, on vérifie le token (filtre jwtFiler)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        ///!\ Rappel : le retour est un security filter chain
        return http.build();
    }

    @Bean
    //Pour crypter les mots de passe
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
