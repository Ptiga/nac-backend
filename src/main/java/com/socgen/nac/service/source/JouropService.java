package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Jourop;

import java.util.ArrayList;
import java.util.List;

public class JouropService implements JouropServiceInterface{

    List<Jourop> listeDetailJourop = new ArrayList<>();

    @Override
    public Jourop createJourop(String sourceFilename, String codeFonds, String categorie, String transactionType, String isinValeur, String tradeDate, double tradePrice, String deviseCours) {
        return new Jourop(sourceFilename, codeFonds, categorie, transactionType, isinValeur, tradeDate, tradePrice, deviseCours);
    }
}
