package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.service.source.JouropService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JouropServiceTest {

    String sourceFilename = "jourop_kdja_FD0004_20220630_010722105654.fic";
    String codeFonds = "FD0004";
    String categorie = "CAT ";
    String transactionType = "CCT ";
    String isinValeur = "OACT01584483";
    String tradeDate = "20220630";
    double tradePrice =         23538976.39        ;
    String deviseCours = "USD";

    JouropService jouropService = new JouropService();

    @Test
    public void createJourop(){
        Jourop jourop = jouropService.createJourop(sourceFilename, codeFonds, categorie, transactionType, isinValeur, tradeDate, tradePrice, deviseCours);
        Assertions.assertEquals(sourceFilename, jourop.getSourceFilename());
        Assertions.assertEquals(codeFonds, jourop.getCodeFonds());
        Assertions.assertEquals(categorie, jourop.getCategorie());
        Assertions.assertEquals(transactionType, jourop.getTransactionType());
        Assertions.assertEquals(isinValeur, jourop.getIsinValeur());
        Assertions.assertEquals(tradeDate, jourop.getTradeDate());
        Assertions.assertEquals(tradePrice, jourop.getTradePrice());
        Assertions.assertEquals(deviseCours, jourop.getDeviseCours());
    }




}
