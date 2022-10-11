package com.socgen.nac.service.check;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.service.check.CheckFluctuationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CheckFluctuationServiceTest {

    String filenameV = "vinvca_kdja_FD0004_20220629_300622091342.fic";
    String fondsV = "FD0004";
    String nomFondsV = "FUND_NUM_04";
    String deviseFondsV = "EUR";
    String dataValoV = "20220630";
    String triComptableV = "0";
    String categorieV = "VMOB";
    String isinV = "FR0000120321";
    String libelleValeurV = "LOREAL SA";
    String dateCoursV = "20220629";
    double prixV = 326.0;
    String deviseV = "EUR";

    Vinvca vinvca = new Vinvca(filenameV, fondsV, nomFondsV, deviseFondsV, dataValoV, triComptableV, categorieV, isinV, libelleValeurV, dateCoursV, prixV, deviseV);


    String filenameJ = "invcah_kdja_FD0004_20220630_010722105654";
    String fondsJ = "FD0004";
    String nomFondsJ = "FUND_NUM_04";
    String deviseFondsJ = "EUR";
    String dataValoJ = "20220630";
    String triComptableJ = "0";
    String categorieJ = "VMOB";
    String isinJ = "FR0000120321";
    String libelleValeurJ = "LOREAL SA";
    String dateCoursJ = "20220630";
    double prixJ = 322.1;
    String deviseJ = "EUR";

    Invcah invcah = new Invcah(filenameJ, fondsJ, nomFondsJ, deviseFondsJ, dataValoJ, triComptableJ, categorieJ, isinJ, libelleValeurJ, dateCoursJ, prixJ, deviseJ);


    String sourceFilename = "jourop_kdja_FD0004_20220630_010722105654.fic";
    String codeFonds = "FD0004";
    String categorie = "CAT ";
    String transactionType = "CCT ";
    String isinValeur = "OACT01584483";
    String tradeDate = "20220630";
    double tradePrice =         23538976.39        ;
    String deviseCours = "USD";

    Jourop jourop = new Jourop(sourceFilename, codeFonds, categorie, transactionType, isinValeur, tradeDate, tradePrice, deviseCours);


    double threshold0 = 0.05;
    double threshold1 = 0.015;
    double threshold2 = 0.005;
    double threshold3 = 0.02;
    double threshold4 = 0.03;
    double threshold5 = 0.3;
    double threshold6 = 0.1;
    double threshold7 = 1;
    double thresholdT = 1;

    Map<String, Double> thresholds = new HashMap<>();


    CheckFluctuationService checkFluctuationService = new CheckFluctuationService();

    public Map fillThresholdMap(Map map){
        thresholds.put("0", threshold0);
        thresholds.put("1", threshold1);
        thresholds.put("2", threshold2);
        thresholds.put("3", threshold3);
        thresholds.put("4", threshold4);
        thresholds.put("5", threshold5);
        thresholds.put("6", threshold6);
        thresholds.put("7", threshold7);
        thresholds.put("T", thresholdT);
        return map;
    }
    

    @Test
    public void calculateFluctuation(){
        Assertions.assertEquals(Math.abs((prixJ-prixV)/prixV), checkFluctuationService.calculateFluctuation(vinvca, invcah));
    }

    @Test
    public void retrieveThreshold(){
        thresholds = fillThresholdMap(thresholds);
        System.out.println(thresholds);
        Assertions.assertEquals(0.05, checkFluctuationService.retrieveTheshold(thresholds, invcah.getTriComptable()));
    }

    @Test
    public void compareInvcahAndVinvca(){
        Assertions.assertTrue(checkFluctuationService.compareInvcahAndVinvca(invcah, vinvca));
    }

    @Test
    public void compareInvcahAndJourop(){
        Assertions.assertFalse(checkFluctuationService.compareInvcahAndJourop(invcah, jourop));
    }

    /*
    //Test à finir
    @Test
    public void createCheckFluctuationData(){
        Assertions.assertNotNull(checkFluctuationService.createCheckFluctuationData(invcah));
    }
    */
}
