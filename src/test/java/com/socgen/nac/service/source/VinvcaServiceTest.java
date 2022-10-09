package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.service.source.VinvcaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VinvcaServiceTest{

    String filename = "vinvca_kdja_FD0004_20220629_300622091342.fic";
    String fonds = "FD0004";
    String nomFonds = "FUND_NUM_04";
    String deviseFonds = "EUR";
    String dataValo = "20220630";
    String triComptable = "0";
    String categorie = "VMOB";
    String isin = "FR0000120321";
    String libelleValeur = "LOREAL SA";
    String dateCours = "20220629";
    double prix = 326.0;
    String devise = "EUR";

    //Le "= new VinvcaService()" est-il obligatoire ?
    //VinvcaService vinvcaService;
    VinvcaService vinvcaService = new VinvcaService();

    @Test
    public void testVinvcaConstructor(){
        Vinvca vinvca = vinvcaService.createVinvca(filename, fonds, nomFonds, deviseFonds, dataValo, triComptable, categorie, isin, libelleValeur, dateCours, prix, devise);
        Assertions.assertEquals(filename, vinvca.getSourceFilename());
        Assertions.assertEquals(fonds, vinvca.getCodeFonds());
        Assertions.assertEquals(nomFonds, vinvca.getNomfonds());
        Assertions.assertEquals(deviseFonds, vinvca.getDeviseFonds());
        Assertions.assertEquals(dataValo, vinvca.getDateVL());
        Assertions.assertEquals(triComptable, vinvca.getTriComptable());
        Assertions.assertEquals(categorie, vinvca.getCategorie());
        Assertions.assertEquals(isin, vinvca.getIsinValeur());
        Assertions.assertEquals(libelleValeur, vinvca.getLibeleValeur());
        Assertions.assertEquals(dateCours, vinvca.getDateCours());
        Assertions.assertEquals(prix, vinvca.getCours());
        Assertions.assertEquals(devise, vinvca.getDeviseCours());
    }


}
