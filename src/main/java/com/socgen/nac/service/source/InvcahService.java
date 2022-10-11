package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvcahService implements InvcahServiceInterface {

    List<Invcah> listeDetailInvcah = new ArrayList<>();

    @Override
    public Invcah createInvcah(String filename, String codeFonds, String nomFonds, String deviseFonds, String dateValo, String triComptable, String categorie, String isin, String libelleValeur, String dateCours, double cours, String deviseCours) {
        return new Invcah(filename, codeFonds, nomFonds, deviseFonds, dateValo, triComptable, categorie, isin, libelleValeur, dateCours, cours, deviseCours);
    }




}
