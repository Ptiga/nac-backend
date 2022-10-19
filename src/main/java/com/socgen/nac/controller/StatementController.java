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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StatementController implements StatementControllerInterface{

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
/*
    @GetMapping("/get-result")
    public ResponseEntity<List<Result>> getResult(){

        System.out.println("On passe par la méthode de génération des résultats");

        List<Result>listeResultat = new ArrayList<>();
        listeResultat = resultService.fromSourceFolderToResultList();

        System.out.println("Taille liste des résultats : " + listeResultat.size());

        //return ResponseEntity.ok(resultService.fromSourceFolderToResultList());
        return ResponseEntity.ok(listeResultat);
    }
*/

    @GetMapping("/load-save")
    public void loadAndSave(){

        System.out.println("On passe par la méthode de sauvegarde des états en base");

        //List<Result>listeResultat = new ArrayList<>();
        //listeResultat = resultService.fromSourceFolderToResultList();
        int nbSaves = statementService.loadAndSaveStatements();

        System.out.format("Traitement terminé : %d état(s) sauvegardé(s)", nbSaves);

    }

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
    @GetMapping("/results/result/{resultId}")
    public ResponseEntity<Optional> getRequiredResult(@PathVariable("resultId") String resultId) {

        System.out.println("Méthode pour obtenir les résultats");

        Optional<Result> requiredResult = resultService.getSelectedResult(resultId);

        //System.out.println("Taille liste des résultats : " + listeResultat.size());

        //return ResponseEntity.ok(resultService.fromSourceFolderToResultList());
        return ResponseEntity.ok(requiredResult);
    }

    //@RequestMapping(value = "/results/{resultId}", produces = "application/json", method=RequestMethod.PUT)
    @PutMapping(value = "/results/{resultId}")
    public ResponseEntity updateResult(@PathVariable("resultId") String resultId, @Valid @RequestBody Result result){
        return resultService.updateResult(resultId, result);
    }
            }
