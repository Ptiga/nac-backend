package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;

public interface InvcahServiceInterface {
    Invcah createInvcah(String filename, String codeFonds, String nomFonds, String deviseFonds, String dateValo, String triComptable, String categorie, String isin, String libelleValeur, String dateCours, double cours, String deviseCours);
}
