package com.socgen.nac.controller;

import com.socgen.nac.entity.result.Result;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import com.socgen.nac.service.check.CheckFluctuationServiceInterface;
import com.socgen.nac.service.result.ResultServiceInterface;
import com.socgen.nac.service.source.InvcahServiceInterface;
import com.socgen.nac.service.source.JouropServiceInterface;
import com.socgen.nac.service.source.StatementServiceInterface;
import com.socgen.nac.service.source.VinvcaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatementController implements StatementControllerInterface{

    @Autowired
    private ResultServiceInterface resultService;

    @Autowired
    private StatementServiceInterface statementService;


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

    @GetMapping("/get-result")
    public ResponseEntity<List<Result>> getResult(){

        System.out.println("On passe par la méthode de génération des résultats");

        List<Result>listeResultat = new ArrayList<>();
        listeResultat = resultService.fromSourceFolderToResultList();

        System.out.println("Taille liste des résultats : " + listeResultat.size());

        //return ResponseEntity.ok(resultService.fromSourceFolderToResultList());
        return ResponseEntity.ok(listeResultat);
    }


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
    @GetMapping("/get-results")
    public ResponseEntity<List<Result>> getCheckResult() {

        System.out.println("Méthode pour obtenir les résultats");

        List<Result>listeResultat = resultService.retrieveResults();

        System.out.println("Taille liste des résultats : " + listeResultat.size());

        //return ResponseEntity.ok(resultService.fromSourceFolderToResultList());
        return ResponseEntity.ok(listeResultat);
    }
}
