package com.socgen.nac.controller;

import com.socgen.nac.entity.result.Result;
import com.socgen.nac.service.result.ResultServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class ResultController implements ResultControllerInterface {

    @Autowired
    private ResultServiceInterface resultService;


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
    public ResponseEntity updateResult(@PathVariable("resultId") String resultId, @RequestBody Result result, Principal principal){
       //Prendre en compte la vérification du user pour renvoyer les info au client
        return resultService.updateResult(resultId, result);
    }



}
