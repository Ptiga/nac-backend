package com.socgen.nac.service;

import com.socgen.nac.entity.Invcah;

public class InvcahService implements InvcahServiceInterface {
    @Override
    public Invcah createInvcah(String filename, String codeFonds, String nomFonds, String deviseFonds, String dateValo, String isin, String libelleValeur, String dateCours, double cours, String deviseCours) {
        return new Invcah(filename, codeFonds, nomFonds, deviseFonds, dateValo, isin, libelleValeur, dateCours, cours, deviseCours);
    }
}
