package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Jourop;

public interface JouropServiceInterface {
    Jourop createJourop(String sourceFilename, String codeFonds, String categorie, String transactionType, String isinValeur, String tradeDate, double tradePrice, String deviseCours);
}
