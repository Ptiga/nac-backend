package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Threshold;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.file.SourceFileRepository;
import com.socgen.nac.service.check.CheckFluctuationService;
import com.socgen.nac.service.source.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ResultServiceTest {

    String sourceFolder = "c://temp//GP3_files_test//";
    char dataSeparator = '_';
    int numberOfSeparator = 4;

    SourceFileRepository sourceFileRepository = new SourceFileRepository(sourceFolder, dataSeparator, numberOfSeparator);

    InvcahService invcahService = new InvcahService();
    VinvcaService vinvcaService = new VinvcaService();
    JouropService jouropService = new JouropService();
    StatementService statementService = new StatementService(sourceFileRepository);

    CheckFluctuationService checkFluctuationService = new CheckFluctuationService(invcahService, vinvcaService, jouropService);

    ResultService resultService = new ResultService(sourceFileRepository, checkFluctuationService, invcahService, vinvcaService, jouropService, statementService);

    List<Vinvca> listVinvca = new ArrayList<>();
    List<Jourop>listJourop = new ArrayList<>();
    List<Threshold>thresholds = createThresholdsForTest();
    List<CheckFluctuationData>listCheckFluctuation = new ArrayList<>();
    List<Result> resultList = new ArrayList<>();

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
    public void retrieveNoAlerts(){
        Vinvca vinvca = new Vinvca("vinvca_kdja_FD0004_20220629_300622091342.fic",
                "FD0004", "FUND_NUM_04", "EUR","20220630",
                "0","VMOB","FR0000120321","LOREAL SA",
                "20220629",326.0,"EUR");
        Invcah invcah = new Invcah("invcah_kdja_FD0004_20220630_010722105654",
                "FD0004","FUND_NUM_04","EUR","20220630",
                "0","VMOB","FR0000120321","LOREAL SA",
                "20220630",322.1,"EUR");

        listVinvca.add(vinvca);
        listCheckFluctuation.add(checkFluctuationService.createCheckFluctuationData(invcah, listVinvca, listJourop, thresholds));
        resultList = resultService.createResultFluctuationCheck(listCheckFluctuation);
        Assertions.assertTrue(resultList.size()==0);
    }

    @Test
    public void retrieveAlerts(){
        Vinvca vinvca = new Vinvca("vinvca_kdja_FD0004_20220629_300622091342.fic",
                "FD0004", "FUND_NUM_04", "EUR","20220630",
                "0","VMOB","FR0000120321","LOREAL SA",
                "20220629",326.0,"EUR");
        Invcah invcah = new Invcah("invcah_kdja_FD0004_20220630_010722105654",
                "FD0004","FUND_NUM_04","EUR","20220630",
                "0","VMOB","FR0000120321","LOREAL SA",
                "20220630",360.0,"EUR");

        listVinvca.add(vinvca);
        listCheckFluctuation.add(checkFluctuationService.createCheckFluctuationData(invcah, listVinvca, listJourop, thresholds));
        resultList = resultService.createResultFluctuationCheck(listCheckFluctuation);
        Assertions.assertTrue(resultList.size()==1);
    }
/*
    @Test
    public void fromFolderToResult(){
        resultList = resultService.fromSourceFolderToResultList();
        Assertions.assertNotNull(resultList);
    }
*/
}
