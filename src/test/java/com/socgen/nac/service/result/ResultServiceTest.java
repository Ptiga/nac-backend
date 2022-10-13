package com.socgen.nac.service.result;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.file.SourceFileRepository;
import com.socgen.nac.service.check.CheckFluctuationService;
import com.socgen.nac.service.source.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        checkFluctuationService.getVinvcaService().addVinvcaToList(vinvca);
        checkFluctuationService.createCheckFluctuationData(invcah);
        resultService.createResultFluctuationCheck(checkFluctuationService.getListeCheckFluctuation());
        Assertions.assertTrue(resultService.getResultList().size()==0);
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

        checkFluctuationService.getVinvcaService().addVinvcaToList(vinvca);
        checkFluctuationService.createCheckFluctuationData(invcah);
        resultService.createResultFluctuationCheck(checkFluctuationService.getListeCheckFluctuation());
        Assertions.assertTrue(resultService.getResultList().size()==1);
    }

    @Test
    public void fromFolderToResult(){
        resultService.fromSourceFolderToResultList();
        Assertions.assertNotNull(resultService.getResultList());
    }

}
