package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.entity.source.Vinvca;
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
    public List<Result> fromSourceFolderToResultList() {
        //Création des listes utilisées
        //Liste des fichiers du répertoire source
        List<Statement> listOfFiles = new ArrayList<>();
        List<String[]>extractedList = new ArrayList<>();
        List<Invcah> listeDetailInvcah = new ArrayList<>();
        List<Vinvca> listeDetailVinvca = new ArrayList<>();
        List<Jourop> listeDetailJourop = new ArrayList<>();
        List<CheckFluctuationData>listCheckFluctuationData = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();

        //On liste les fichiers
        listOfFiles = sourceFileRepository.listFiles();

        //On défini si l'état est utilisable
        statementService.manageListOfFunds(listOfFiles);

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
        resultList = createResultFluctuationCheck(listCheckFluctuationData);
        System.out.println("Taille Result : " + resultList.size());

        return resultList;
    }


}
