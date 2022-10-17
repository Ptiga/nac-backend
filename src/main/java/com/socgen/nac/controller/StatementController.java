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
/*
    @Autowired
    private StatementServiceInterface statementService;


    public StatementServiceInterface getStatementService(){
        return statementService;
    }

    public void setStatementService(StatementServiceInterface statementService) {
        this.statementService = statementService;
    }

*/
    /*
    @Autowired
    private SourceFileRepositoryInterface sourceFileRepository;

    public SourceFileRepositoryInterface getSourceFileRepository() {
        return sourceFileRepository;
    }

    public void setSourceFileRepository(SourceFileRepositoryInterface sourceFileRepository) {
        this.sourceFileRepository = sourceFileRepository;
    }

    @Autowired
    private CheckFluctuationServiceInterface checkFluctuationService;

    public CheckFluctuationServiceInterface getCheckFluctuationService() {
        return checkFluctuationService;
    }

    public void setCheckFluctuationService(CheckFluctuationServiceInterface checkFluctuationService) {
        this.checkFluctuationService = checkFluctuationService;
    }

    @Autowired
    private InvcahServiceInterface invcahService;

    public InvcahServiceInterface getInvcahService() {
        return invcahService;
    }

    public void setInvcahService(InvcahServiceInterface invcahService) {
        this.invcahService = invcahService;
    }

    @Autowired
    private VinvcaServiceInterface vinvcaService;

    public VinvcaServiceInterface getVinvcaService() {
        return vinvcaService;
    }

    public void setVinvcaService(VinvcaServiceInterface vinvcaService) {
        this.vinvcaService = vinvcaService;
    }

    @Autowired
    private JouropServiceInterface jouropService;

    public JouropServiceInterface getJouropService() {
        return jouropService;
    }

    public void setJouropService(JouropServiceInterface jouropService) {
        this.jouropService = jouropService;
    }

    @Autowired
    private StatementServiceInterface statementService;

    public StatementServiceInterface getStatementService() {
        return statementService;
    }

    public void setStatementService(StatementServiceInterface statementService) {
        this.statementService = statementService;
    }
*/
    @Autowired
    private ResultServiceInterface resultService;

    @Autowired
    private StatementServiceInterface statementService;
/*
    public ResultServiceInterface getResultService() {
        return resultService;
    }

    public void setResultService(SourceFileRepositoryInterface sourceFileRepository, CheckFluctuationServiceInterface checkFluctuationService, InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService, StatementServiceInterface statementService) {
        this.sourceFileRepository = sourceFileRepository;
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
        this.statementService = statementService;
        this.checkFluctuationService = checkFluctuationService;
    }
*/
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

    //Méthode de création d'état (check dans dossier)

    //Interface avec endpoints/méthodes GET/POST/UPDATE

}
