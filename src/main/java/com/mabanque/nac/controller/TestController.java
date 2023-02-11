/*
package com.mabanque.nac.controller;

import com.mabanque.nac.entity.source.Fund;
import com.mabanque.nac.entity.user.User;
import com.mabanque.nac.service.result.ResultServiceInterface;
import com.mabanque.nac.service.source.FundServiceInterface;
import com.mabanque.nac.service.source.StatementServiceInterface;
import com.mabanque.nac.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ResultServiceInterface resultService;

    @Autowired
    private StatementServiceInterface statementService;

    @Autowired
    private FundServiceInterface fundService;

    @Autowired
    private UserServiceInterface userService;


    @GetMapping("/home")
    public ResponseEntity<String> home(){

        System.out.println("Je passe par home");

        return ResponseEntity.ok("Ok, ça marche !");
    }

    @GetMapping("/test-fund")
    public ResponseEntity<List<Fund>> testFund(){
        List<Fund>findInfo = new ArrayList<>();
        findInfo =  fundService.getFundInformation();
        return ResponseEntity.ok(findInfo);
    }

    @GetMapping("/test-user")
    public ResponseEntity<List<User>> testUser(){
        List<User>users = new ArrayList<>();
        users =  userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/load-save")
    public void loadAndSave(){

        System.out.println("On passe par la méthode de sauvegarde des états en base");

        int nbSaves = statementService.loadAndSaveStatements();

        System.out.format("Traitement terminé : %d état(s) sauvegardé(s)", nbSaves);

    }

}
*/