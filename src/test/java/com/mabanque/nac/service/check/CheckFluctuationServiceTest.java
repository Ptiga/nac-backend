package com.mabanque.nac.service.check;

import com.mabanque.nac.entity.source.Invcah;
import com.mabanque.nac.entity.source.Jourop;
import com.mabanque.nac.entity.source.Threshold;
import com.mabanque.nac.entity.source.Vinvca;
import com.mabanque.nac.repository.database.InvcahRepositoryInterface;
import com.mabanque.nac.repository.database.JouropRepositoryInterface;
import com.mabanque.nac.repository.database.ThresholdRepositoryInterface;
import com.mabanque.nac.repository.database.VinvcaRepositoryInterface;
import com.mabanque.nac.service.source.InvcahService;
import com.mabanque.nac.service.source.JouropService;
import com.mabanque.nac.service.source.VinvcaService;
import com.mabanque.nac.entity.check.CheckFluctuationData;
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
    String codeOperation = "296557221";
    String codeFonds = "FD0004";
    String categorie = "CAT ";
    String transactionType = "CCT ";
    String isinValeur = "OACT01584483";
    String tradeDate = "20220630";
    double tradePrice =         23538976.39        ;
    String deviseCours = "USD";

    Jourop jourop = new Jourop(codeOperation, sourceFilename, codeFonds, categorie, transactionType, isinValeur, tradeDate, tradePrice, deviseCours);

    InvcahRepositoryInterface invcahRepository;
    VinvcaRepositoryInterface vinvcaRepository;
    JouropRepositoryInterface jouropRepository;


    InvcahService invcahService = new InvcahService(invcahRepository);
    VinvcaService vinvcaService = new VinvcaService(vinvcaRepository);
    JouropService jouropService = new JouropService(jouropRepository);
    ThresholdRepositoryInterface thresholdRepository;

    CheckFluctuationService checkFluctuationService = new CheckFluctuationService(invcahService, vinvcaService, jouropService, thresholdRepository);

    List<Threshold>thresholds = createThresholdsForTest();
    List<Vinvca>listVinvca = new ArrayList<>();
    List<Jourop>listJourop = new ArrayList<>();
    List<CheckFluctuationData>listCheckFluctuation = new ArrayList<>();

    private List<Threshold> createThresholdsForTest(){
        List<Threshold>thresholds = new ArrayList<>();
            thresholds.add(new Threshold("0", "Securities",0.05));
            thresholds.add(new Threshold("1", "Bonds",0.015));
            thresholds.add(new Threshold("2", "Debt securities",0.005));
            thresholds.add(new Threshold("3", "Ucits - ETF",0.02));
            thresholds.add(new Threshold("4", "Ucits",0.02));
            thresholds.add(new Threshold("5", "Futures",0.03));
            thresholds.add(new Threshold("6", "Options",0.3));
            thresholds.add(new Threshold("7", "Swap",1.0));
            thresholds.add(new Threshold("T", "X-currencies", 1.0));
        return thresholds;
    }

    @Test
    public void retrieveThreshold(){
        //thresholds = fillThresholdMap(thresholds);
        //System.out.println(checkFluctuationService.getThresholds());
        Assertions.assertEquals(0.05, checkFluctuationService.searchThresholdByTriComptable(thresholds, invcah.getTriComptable()).getThreshold());
    }

    @Test
    public void compareInvcahAndVinvca(){
        Assertions.assertTrue(checkFluctuationService.compareInvcahAndVinvca(invcah, vinvca));
    }

    @Test
    public void compareInvcahAndJourop(){
        Assertions.assertFalse(checkFluctuationService.compareInvcahAndJourop(invcah, jourop));
    }

    @Test
    public void isInvcahUsable(){
        Assertions.assertTrue(checkFluctuationService.isInvcahUsableForCheck(invcah));
    }

    @Test
    public void isCategoryCorrect(){
        Assertions.assertTrue(checkFluctuationService.isCategoryCorrect(invcah.getCategorie()));
        Assertions.assertTrue(checkFluctuationService.isCategoryCorrect("FUTU"));
        Assertions.assertTrue(checkFluctuationService.isCategoryCorrect("OPTI"));
        Assertions.assertFalse(checkFluctuationService.isCategoryCorrect("CPON"));
        Assertions.assertFalse(checkFluctuationService.isCategoryCorrect("SWAT"));
    }

    @Test
    public void isTriComptableCorrect(){
        Assertions.assertTrue(checkFluctuationService.isTriComptableCorrect(invcah.getTriComptable()));
        Assertions.assertTrue(checkFluctuationService.isTriComptableCorrect("0"));
        Assertions.assertTrue(checkFluctuationService.isTriComptableCorrect("4"));
        Assertions.assertTrue(checkFluctuationService.isTriComptableCorrect("6"));
        Assertions.assertFalse(checkFluctuationService.isTriComptableCorrect("7"));
        Assertions.assertFalse(checkFluctuationService.isTriComptableCorrect("T"));
    }

    @Test
    public void createCheckFluctuationDataWithVinvca(){
        listVinvca.add(vinvca);

        listCheckFluctuation.add(checkFluctuationService.createCheckFluctuationData(invcah, listVinvca, listJourop, thresholds));
        Assertions.assertTrue(listCheckFluctuation.size()>0);
        Assertions.assertEquals(0.05, listCheckFluctuation.get(0).getThreshold().getThreshold());
        Assertions.assertEquals(Math.abs((invcah.getCours()-vinvca.getCours())/vinvca.getCours()), listCheckFluctuation.get(0).getFluctuation());
        Assertions.assertNull(listCheckFluctuation.get(0).getAlertType());
    }

    @Test
    public void createCheckFluctuationDataWithJourop(){
        listJourop.add(jourop);
        listCheckFluctuation.add(checkFluctuationService.createCheckFluctuationData(invcah, listVinvca, listJourop, thresholds));
        Assertions.assertNull(listCheckFluctuation.get(0));
    }
}
