package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;
import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import com.socgen.nac.service.check.CheckFluctuationServiceInterface;
import com.socgen.nac.service.source.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    private CheckFluctuationServiceInterface checkFluctuationService;

    private InvcahServiceInterface invcahService;


    private VinvcaServiceInterface vinvcaService;

    private JouropServiceInterface jouropService;

    private StatementServiceInterface statementService;



    public ResultService(SourceFileRepositoryInterface sourceFileRepository, CheckFluctuationServiceInterface checkFluctuationService, InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService, StatementServiceInterface statementService){
        this.sourceFileRepository = sourceFileRepository;
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
        this.statementService = statementService;
        this.checkFluctuationService = checkFluctuationService;
    }


    //Importer Repository ?
    //Importer StatementService ?
    //Importer CheckFluctuationService (+ services Invcah, Vinvca et Jourop)


    private List<Result> resultList = new ArrayList<>();

    public List<Result> getResultList() {
        return resultList;
    }

    @Override
    public void createResultFluctuationCheck(List<CheckFluctuationData> listeCheckFluctuation) {
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
            }

    private boolean isFluctuationDataContainsVinvca(CheckFluctuationData checkFluctuationData) {
        if (checkFluctuationData.getVinvca()!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void fromSourceFolderToResultList() {
        //On liste les fichiers
        List<Statement> listOfFiles = new ArrayList<>();
        listOfFiles = sourceFileRepository.listFiles();
        //On défini si l'état est utilisable
        statementService.manageListOfFunds(listOfFiles);
        //On met les états dans la liste dédiée
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        //On extrait les données des fichiers invcah
        List<String[]>extractedList = new ArrayList<>();
        extractedList = statementService.createStatementDetail(statementService.getDedicatedList("invcah"));
        invcahService.createInvcahAndAddToList(extractedList);
        //On extrait les données des fichiers vinvca
        extractedList = statementService.createStatementDetail(statementService.getDedicatedList("vinvca"));
        vinvcaService.createVinvcaAndAddToList(extractedList);
        //On extrait les données des fichiers jourop
        extractedList = statementService.createStatementDetail(statementService.getDedicatedList("jourop"));
        jouropService.createJouropAndAddToList(extractedList);
        System.out.println("taille liste invcah: " + invcahService.getListeDetailInvcah().size());
        //On créée la liste des éléments utilisables pour le contrôle
        checkFluctuationService.createCheckDataFromInvcahList(invcahService.getListeDetailInvcah());
        System.out.println("taille liste checkFluct: " + checkFluctuationService.getListeCheckFluctuation().size());
        //On récupère crée une liste avec uniquement les alertes
        createResultFluctuationCheck(checkFluctuationService.getListeCheckFluctuation());
        System.out.println("Taille Result : " + resultList.size());
    }


}
