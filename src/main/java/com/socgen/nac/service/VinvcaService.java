package com.socgen.nac.service;

import com.socgen.nac.entity.Vinvca;

public class VinvcaService implements VinvcaServiceInterface {


    @Override
    public Vinvca createVinvca(String filename, String codeFonds, String nomFonds, String deviseFonds, String dateValo, String isin, String libelleValeur, String dateCours, double cours, String deviseCours) {
        return new Vinvca(filename, codeFonds, nomFonds, deviseFonds, dateValo, isin, libelleValeur, dateCours, cours, deviseCours);
    }
}
