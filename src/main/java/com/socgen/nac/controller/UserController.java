package com.socgen.nac.controller;

import com.socgen.nac.entity.user.User;
import com.socgen.nac.jwt.JwtUtils;
import com.socgen.nac.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserControllerInterface{


    @Autowired
    JwtUtils jwtutils;


    @Autowired
    private UserServiceInterface userService;

    @Override
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    @PostMapping("/create-user")
    public ResponseEntity addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @Override
    @GetMapping("/isConnected")
    public ResponseEntity isUserConnected() {
        //Après validation du token, on récupère le contexte utilisateur
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            return new ResponseEntity((((UserDetails) principal).getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity("Utilisateur non connecté", HttpStatus.FORBIDDEN);
    }
}
