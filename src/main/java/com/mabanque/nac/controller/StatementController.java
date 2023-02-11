package com.mabanque.nac.controller;

import com.mabanque.nac.service.result.ResultServiceInterface;
import com.mabanque.nac.service.source.FundServiceInterface;
import com.mabanque.nac.service.source.StatementServiceInterface;
import com.mabanque.nac.service.source.*;
import com.mabanque.nac.service.user.UserServiceInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class StatementController implements StatementControllerInterface{

    @Autowired
    private ResultServiceInterface resultService;

    @Autowired
    private StatementServiceInterface statementService;

    @Autowired
    private FundServiceInterface fundService;

    @Autowired
    private UserServiceInterface userService;



    @Override
    @GetMapping("/check-new-statements")
    public ResponseEntity<String> checkNewStatements(){

        System.out.println("Méthoode pour vérifier s'il y a de nouveaux états");

        String nbStatementsAvailable = String.valueOf(statementService.checkNewStatements());

        System.out.format("nombre de nouveaux états: %s", nbStatementsAvailable + "\n");

        return ResponseEntity.ok(nbStatementsAvailable);
    }

    @Override
    @GetMapping("/upload-new-statements")
    public ResponseEntity<String> uploadNewStatements() {

        System.out.println("Méthoode pour charger en base les nouveaux états");

        String nbUploadedStatements = String.valueOf(statementService.saveNewStatements());

        System.out.format("nombre d'états chargés en base: %s", nbUploadedStatements + "\n");

        return ResponseEntity.ok(nbUploadedStatements);
    }
}
