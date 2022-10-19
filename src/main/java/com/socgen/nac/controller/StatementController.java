package com.socgen.nac.controller;

import com.socgen.nac.entity.result.Result;
import com.socgen.nac.entity.source.Fund;
import com.socgen.nac.entity.user.User;
import com.socgen.nac.repository.database.FundRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import com.socgen.nac.service.check.CheckFluctuationServiceInterface;
import com.socgen.nac.service.result.ResultServiceInterface;
import com.socgen.nac.service.source.*;
import com.socgen.nac.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@EnableWebSecurity
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
    @GetMapping("/Check-new-statements")
    public ResponseEntity<String> checkNewStatements(){

        System.out.println("Méthoode pour vérifier s'il y a de nouveaux états");

        String nbStatementsAvailable = String.valueOf(statementService.checkNewStatements());

        System.out.format("nombre de nouveaux états: %s", nbStatementsAvailable);

        return ResponseEntity.ok(nbStatementsAvailable);
    }

    @Override
    @GetMapping("/upload-new-statements")
    public ResponseEntity<String> uploadNewStatements() {

        System.out.println("Méthoode pour charger en base les nouveaux états");

        String nbUploadedStatements = String.valueOf(statementService.saveNewStatements());

        System.out.format("nombre d'états chargés en base: %s", nbUploadedStatements);

        return ResponseEntity.ok(nbUploadedStatements);
    }
/*
    @Override
    @GetMapping("/results")
    public ResponseEntity<List<Result>> getCheckResult() {

        System.out.println("Méthode pour obtenir les résultats");

        List<Result>listeResultat = resultService.retrieveResults();

        System.out.println("Taille liste des résultats : " + listeResultat.size());

        //return ResponseEntity.ok(resultService.fromSourceFolderToResultList());
        return ResponseEntity.ok(listeResultat);
    }

    @Override
    @GetMapping("/results/{resultId}")
    public ResponseEntity<Optional> getRequiredResult(@PathVariable("resultId") String resultId) {

        System.out.println("Méthode pour obtenir les résultats");

        Optional<Result> requiredResult = resultService.getSelectedResult(resultId);

        //System.out.println("Taille liste des résultats : " + listeResultat.size());

        //return ResponseEntity.ok(resultService.fromSourceFolderToResultList());
        return ResponseEntity.ok(requiredResult);
    }

    //@RequestMapping(value = "/results/{resultId}", produces = "application/json", method=RequestMethod.PUT)
    @PutMapping(value = "/results/{resultId}")
    public ResponseEntity updateResult(@PathVariable("resultId") String resultId, @RequestBody Result result){
        return resultService.updateResult(resultId, result);
    }

 */
            }
