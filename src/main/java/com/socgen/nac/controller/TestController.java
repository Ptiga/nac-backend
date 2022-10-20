package com.socgen.nac.controller;

import com.socgen.nac.entity.source.Fund;
import com.socgen.nac.entity.user.User;
import com.socgen.nac.service.result.ResultServiceInterface;
import com.socgen.nac.service.source.FundServiceInterface;
import com.socgen.nac.service.source.StatementServiceInterface;
import com.socgen.nac.service.user.UserServiceInterface;
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


    @GetMapping("/")
    public ResponseEntity<String> TestLocal(){
        //Statement statement = new Statement();

        System.out.println("Je passe par home");

        return ResponseEntity.ok("Ok, ça marche !");
    /*
    public ResponseEntity<Statement> home(){
        //Statement statement = new Statement();

        return ResponseEntity.ok(statement);
     */
    }


    @GetMapping("/home")
    public ResponseEntity<String> home(){
        //Statement statement = new Statement();

        System.out.println("Je passe par home");

        return ResponseEntity.ok("Ok, ça marche !");
    /*
    public ResponseEntity<Statement> home(){
        //Statement statement = new Statement();

        return ResponseEntity.ok(statement);
     */
    }

    @GetMapping("/test-fund")
    public ResponseEntity<List<Fund>> testFund(){
        List<Fund>findInfo = new ArrayList<>();
        findInfo =  fundService.getFundInformation();
        //findInfo = fundService.getFundByTeam("BU-A");
        return ResponseEntity.ok(findInfo);
    }

    @GetMapping("/test-user")
    public ResponseEntity<List<User>> testUser(){
        List<User>users = new ArrayList<>();
        users =  userService.getUsers();
        //findInfo = fundService.getFundByTeam("BU-A");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/load-save")
    public void loadAndSave(){

        System.out.println("On passe par la méthode de sauvegarde des états en base");

        //List<Result>listeResultat = new ArrayList<>();
        //listeResultat = resultService.fromSourceFolderToResultList();
        int nbSaves = statementService.loadAndSaveStatements();

        System.out.format("Traitement terminé : %d état(s) sauvegardé(s)", nbSaves);

    }

}
