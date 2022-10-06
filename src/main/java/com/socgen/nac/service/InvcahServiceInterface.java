package com.socgen.nac.service;

import com.socgen.nac.entity.Invcah;

public interface InvcahServiceInterface {
    Invcah createInvcah(String filename, String fonds, String nomFonds, String deviseFonds, String dataValo, String isin, String libelleValeur, String dateCoursVeille, double prixVeille, String deviseCours);
}
