package com.socgen.nac.service;

import com.socgen.nac.entity.Vinvca;

public interface VinvcaServiceInterface {

    Vinvca createVinvca (String filename, String codeFonds, String nomFonds, String deviseFonds, String dateValo, String isin, String libelleValeur, String dateCoursVeille, double cours, String deviseCours);

}
