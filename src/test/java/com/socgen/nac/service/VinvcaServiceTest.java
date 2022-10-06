package com.socgen.nac.service;

import com.socgen.nac.entity.Vinvca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VinvcaServiceTest{

    String filename = "vinvca_kdja_FD0004_20220629_300622091342.fic";
    String fonds = "FD0004";
    String nomFonds = "FUND_NUM_04";
    String deviseFonds = "EUR";
    String dataValo = "20220630";
    String isin = "FR0000120321";
    String libelleValeur = "LOREAL SA";
    String dateCoursVeille = "20220629";
    double prixVeille = 326.0;
    String deviseCours = "EUR";

    //Le "= new VinvcaService()" est-il obligatoire ?
    //VinvcaService vinvcaService;
    VinvcaService vinvcaService = new VinvcaService();

    @Test
    public void testVinvcaConstructor(){
        Vinvca vinvca = vinvcaService.createVinvca(filename, fonds, nomFonds, deviseFonds, dataValo, isin, libelleValeur, dateCoursVeille, prixVeille, deviseCours);
        Assertions.assertEquals(filename, vinvca.getSourceFilename());
        Assertions.assertEquals(fonds, vinvca.getCodeFonds());
        Assertions.assertEquals(nomFonds, vinvca.getNomfonds());
        Assertions.assertEquals(deviseFonds, vinvca.getDeviseFonds());
        Assertions.assertEquals(dataValo, vinvca.getDateVL());
        Assertions.assertEquals(isin, vinvca.getIsinValeur());
        Assertions.assertEquals(libelleValeur, vinvca.getLibeleValeur());
        Assertions.assertEquals(dateCoursVeille, vinvca.getDateCours());
        Assertions.assertEquals(prixVeille, vinvca.getCours());
        Assertions.assertEquals(deviseCours, vinvca.getDeviseCours());
    }


}
