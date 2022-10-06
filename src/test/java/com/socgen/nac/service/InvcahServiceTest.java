package com.socgen.nac.service;

import com.socgen.nac.entity.Invcah;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvcahServiceTest {


    String filename = "invcah_kdja_FD0004_20220630_010722105654";
    String fonds = "FD0004";
    String nomFonds = "FUND_NUM_04";
    String deviseFonds = "EUR";
    String dataValo = "20220630";
    String isin = "FR0000120321";
    String libelleValeur = "LOREAL SA";
    String dateCoursJour = "20220630";
    double prixJour = 322.1;
    String deviseCours = "EUR";


    InvcahService invcahService = new InvcahService();

    @Test
    public void testInvcahConstructor(){
        Invcah invcah = invcahService.createInvcah(filename, fonds, nomFonds, deviseFonds, dataValo, isin, libelleValeur, dateCoursJour, prixJour, deviseCours);
        Assertions.assertEquals(filename, invcah.getSourceFilename());
        Assertions.assertEquals(fonds, invcah.getCodeFonds());
        Assertions.assertEquals(nomFonds, invcah.getNomfonds());
        Assertions.assertEquals(deviseFonds, invcah.getDeviseFonds());
        Assertions.assertEquals(dataValo, invcah.getDateVL());
        Assertions.assertEquals(isin, invcah.getIsinValeur());
        Assertions.assertEquals(libelleValeur, invcah.getLibeleValeur());
        Assertions.assertEquals(dateCoursJour, invcah.getDateCours());
        Assertions.assertEquals(prixJour, invcah.getCours());
        Assertions.assertEquals(deviseCours, invcah.getDeviseCours());
    }

}
