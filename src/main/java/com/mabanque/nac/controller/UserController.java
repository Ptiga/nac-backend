package com.mabanque.nac.controller;

import com.mabanque.nac.entity.user.User;
import com.mabanque.nac.jwt.JwtUtils;
import com.mabanque.nac.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserControllerInterface{


    @Autowired
    JwtUtils jwtutils;


    @Autowired
    private UserServiceInterface userService;

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    //@CrossOrigin("http://localhost:3000")
    @CrossOrigin("*")
    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @Override
    @GetMapping("/isConnected")
    public ResponseEntity isUserConnected() {
    //public ResponseEntity<?> isUserConnected() {
        //Après validation du token, on récupère le contexte utilisateur
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            String userRole = userService.getUser((((UserDetails) principal).getUsername())).getRole();
            //return new ResponseEntity((((UserDetails) principal).getUsername()), HttpStatus.OK);
            return new ResponseEntity((((UserDetails) principal).getUsername())+"<#>"+userRole, HttpStatus.OK);
            //return new ResponseEntity(userInfo, HttpStatus.OK);
        }
        return new ResponseEntity("Utilisateur non connecté", HttpStatus.FORBIDDEN);
    }


}
