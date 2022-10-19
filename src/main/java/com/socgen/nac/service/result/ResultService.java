package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.database.ResultRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import com.socgen.nac.service.check.CheckFluctuationServiceInterface;
import com.socgen.nac.service.source.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService implements ResultServiceInterface {

    @Autowired
    private SourceFileRepositoryInterface sourceFileRepository;

    public SourceFileRepositoryInterface getSourceFileRepository() {
        return sourceFileRepository;
    }

    public void setSourceFileRepository(SourceFileRepositoryInterface sourceFileRepository) {
        this.sourceFileRepository = sourceFileRepository;
    }

    @Autowired
    private ResultRepositoryInterface resultRepository;

    public ResultRepositoryInterface getResultRepository() {
        return resultRepository;
    }

    public void setResultRepository(ResultRepositoryInterface resultRepository) {
        this.resultRepository = resultRepository;
    }

    private CheckFluctuationServiceInterface checkFluctuationService;
    private InvcahServiceInterface invcahService;
    private VinvcaServiceInterface vinvcaService;
    private JouropServiceInterface jouropService;
    private StatementServiceInterface statementService;

    public ResultService(SourceFileRepositoryInterface sourceFileRepository, CheckFluctuationServiceInterface checkFluctuationService, InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService, StatementServiceInterface statementService, ResultRepositoryInterface resultRepository){
        this.sourceFileRepository = sourceFileRepository;
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
        this.statementService = statementService;
        this.checkFluctuationService = checkFluctuationService;
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> createResultFluctuationCheck(List<CheckFluctuationData> listeCheckFluctuation) {
        List<Result> resultList = new ArrayList<>();
        for (CheckFluctuationData checkFluctuationData: listeCheckFluctuation) {
            if(checkFluctuationData.getAlertType()!=null){
                Result checkResult = new Result(checkFluctuationData.getInvcah(), checkFluctuationData.getFluctuation(), checkFluctuationData.getThreshold(), checkFluctuationData.getAlertType());
                if(isFluctuationDataContainsVinvca(checkFluctuationData)){
                    checkResult.addVinvcaAttributes(checkFluctuationData.getVinvca());
                }else{
                    checkResult.addJouropAttributes(checkFluctuationData.getJourop());
                }
                resultList.add(checkResult);
            }
        }
        return resultList;
    }

    private boolean isFluctuationDataContainsVinvca(CheckFluctuationData checkFluctuationData) {
        if (checkFluctuationData.getVinvca()!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Result> uploadResults(){
        List<Result>uploadedResults = new ArrayList<>();
        Iterable<Result> resultsFromDatabase = resultRepository.findAll();
        resultsFromDatabase.forEach(uploadedResults::add);
        return uploadedResults;
    }

    @Override
    public Optional<Result> getSelectedResult(String resultId) {
        return resultRepository.findById(Long.parseLong(resultId));
    }

    @Override
    public void saveNewResults(List<Result> resultsToSave) {
        resultRepository.saveAll(resultsToSave);
    }

    private boolean isInsideUploadedList(Result result, List<Result> uploadedResults) {
        for (Result uploadedResult: uploadedResults) {
            if (uploadedResult.getCodeFonds().equals(result.getCodeFonds()) &&
                    uploadedResult.getNavDate().equals(result.getNavDate()) &&
                    uploadedResult.getSecurityCode().equals(result.getSecurityCode()) &&
                    uploadedResult.getFluctuation() == result.getFluctuation()){
                return true;
            }
        }
        return false;
    }

    private List<Result> compareResults(List<Result> results, List<Result> uploadedResults) {
        List<Result>newResults = new ArrayList<>();
        for (Result result: results) {
            if (!isInsideUploadedList(result, uploadedResults)){
                newResults.add(result);
            }
        }
        return newResults;
    }

    private List<Result> concatResultLists(List<Result> results, List<Result> uploadedResults) {
        uploadedResults.forEach(results::add);
        return results;
    }



    @Override
    public List<Result> fromSourceFolderToResultList() {
        //Création des listes utilisées
        //Liste des fichiers du répertoire source
        List<Statement> listOfFiles = new ArrayList<>();
        List<String[]>extractedList = new ArrayList<>();
        List<Invcah> listeDetailInvcah = new ArrayList<>();
        List<Vinvca> listeDetailVinvca = new ArrayList<>();
        List<Jourop> listeDetailJourop = new ArrayList<>();
        List<CheckFluctuationData>listCheckFluctuationData = new ArrayList<>();
        List<Result> results = new ArrayList<>();
        List<Result> resultsToSave = new ArrayList<>();

        //On charge la liste des fichiers déjà présents en base
        List<Statement>uploadedStatements = statementService.uploadStatements();

        //On charge la liste des résultats déjà présents en base
        List<Result>uploadedResults = uploadResults();

        //On liste les fichiers
        listOfFiles = sourceFileRepository.listFiles();

        //On défini si l'état est utilisable
        //TODO: Remplacer la liste vide par la liste 'uploadedStatements'
        List<Statement>listeVide = new ArrayList<>();
        statementService.manageListOfFunds(listOfFiles, listeVide);

        //On met les états dans la liste dédiée
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());

        //On extrait les données des fichiers invcah
        extractedList = statementService.createStatementDetail(statementService.getDedicatedList("invcah"));
        listeDetailInvcah = invcahService.createInvcahAndAddToList(extractedList);

        //On extrait les données des fichiers vinvca
        extractedList = statementService.createStatementDetail(statementService.getDedicatedList("vinvca"));
        listeDetailVinvca = vinvcaService.createVinvcaAndAddToList(extractedList);

        //On extrait les données des fichiers jourop
        extractedList = statementService.createStatementDetail(statementService.getDedicatedList("jourop"));
        listeDetailJourop = jouropService.createJouropAndAddToList(extractedList);
        System.out.println("taille liste invcah: " + listeDetailInvcah.size());
        System.out.println("taille liste vinvca: " + listeDetailVinvca.size());
        System.out.println("taille liste jourop: " + listeDetailJourop.size());

        //On créée la liste des éléments utilisables pour le contrôle
        listCheckFluctuationData = checkFluctuationService.createCheckDataFromInvcahList(listeDetailInvcah, listeDetailVinvca, listeDetailJourop);
        System.out.println("taille liste checkFluct: " + listCheckFluctuationData.size());

        //On récupère crée une liste avec uniquement les alertes
        results = createResultFluctuationCheck(listCheckFluctuationData);
        System.out.println("Taille Result : " + results.size());

        compareResults(results, uploadedResults);

        saveNewResults(results);

        return results;
    }

    @Override
    public List<Result> retrieveResults() {
        //On charge les résultats déjà enregistrés en base
        List<Result>uploadedResults = uploadResults();

        //On récupère de la base les états nécessaires au contrôle
        List<Invcah>invcahs = invcahService.getUploadedInvcah();
        List<Vinvca>vinvcas = vinvcaService.getUploadedVinvca();
        List<Jourop>jourops = jouropService.getUploadedJourop();

        //On crée la liste des éléments utilisés pour le contrôle
        List<CheckFluctuationData>checkFluctuations = checkFluctuationService.createCheckDataFromInvcahList(invcahs, vinvcas, jourops);

        //On crée une liste de résultats avec uniquement les éléments en alerte
        List<Result>results = createResultFluctuationCheck(checkFluctuations);

        //On compare les 2 listes de résultats pour isoler les nouveaux résultats
        results = compareResults(results, uploadedResults);

        //On sauvegarde en base les nouveaux résultats
        saveNewResults(results);

        //On concatère les 2 listes de résultats
        results = concatResultLists(results, uploadedResults);

        return results;
    }

    @Override
    public ResponseEntity updateResult(String resultId, Result result) {
        Optional<Result> resultToUpdate = resultRepository.findById(Long.valueOf(resultId));
        if (!resultToUpdate.isPresent()){
            return new ResponseEntity("Result not existing", HttpStatus.BAD_REQUEST);
        }
        Result resultToSave = resultToUpdate.get();
        resultToSave.setOperatorComment(result.getOperatorComment());
        resultToSave.setSupervisorComment(result.getSupervisorComment());
        resultToSave.setResultValidated(result.isResultValidated());

        resultRepository.save(resultToSave);

        return new ResponseEntity(resultToSave, HttpStatus.OK);
    }
}
