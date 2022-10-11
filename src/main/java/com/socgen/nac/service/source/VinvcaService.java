package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Vinvca;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinvcaService implements VinvcaServiceInterface {

    List<Vinvca> listeDetailVinvca = new ArrayList<>();

    @Override
    public Vinvca createVinvca(String filename, String codeFonds, String nomFonds, String deviseFonds, String dateValo, String triComptable, String categorie, String isin, String libelleValeur, String dateCours, double cours, String deviseCours) {
        return new Vinvca(filename, codeFonds, nomFonds, deviseFonds, dateValo, triComptable, categorie, isin, libelleValeur, dateCours, cours, deviseCours);
    }


}
