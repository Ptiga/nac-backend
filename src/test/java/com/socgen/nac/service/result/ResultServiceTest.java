package com.socgen.nac.service.result;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.service.check.CheckFluctuationService;
import com.socgen.nac.service.source.InvcahService;
import com.socgen.nac.service.source.JouropService;
import com.socgen.nac.service.source.VinvcaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultServiceTest {


    InvcahService invcahService = new InvcahService();
    VinvcaService vinvcaService = new VinvcaService();
    JouropService jouropService = new JouropService();

    CheckFluctuationService checkFluctuationService = new CheckFluctuationService(invcahService, vinvcaService, jouropService);

    ResultService resultService = new ResultService();

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

}
