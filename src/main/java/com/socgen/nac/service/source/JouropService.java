package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;

import java.util.ArrayList;
import java.util.List;

public class JouropService implements JouropServiceInterface{

    List<Jourop> listeDetailJourop = new ArrayList<>();


    @Override
    public void createJouropFromList(List<String[]> listDetail) {
        for (String[] valeur: listDetail) {
            listeDetailJourop.add(new Jourop(valeur[0], valeur[1], valeur[2],valeur[6], valeur[3], valeur[9],Double.parseDouble(valeur[14]), valeur[15]));
        }
    }
}
