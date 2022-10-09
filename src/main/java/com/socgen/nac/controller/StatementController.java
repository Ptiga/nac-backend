package com.socgen.nac.controller;

import com.socgen.nac.service.source.StatementServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatementController implements StatementControllerInterface{

    @Autowired
    private StatementServiceInterface statementService;


    public StatementServiceInterface getStatementService(){
        return statementService;
    }

    public void setStatementService(StatementServiceInterface statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        //Statement statement = new Statement();

        return ResponseEntity.ok("Ok, ça marche !");
    /*
    public ResponseEntity<Statement> home(){
        //Statement statement = new Statement();

        return ResponseEntity.ok(statement);
     */
    }


    //Méthode de création d'état (check dans dossier)

    //Interface avec endpoints/méthodes GET/POST/UPDATE

}
