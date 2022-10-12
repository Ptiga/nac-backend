package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JouropService implements JouropServiceInterface{

    List<Jourop> listeDetailJourop = new ArrayList<>();


    public List<Jourop> getListeDetailJourop() {
        return listeDetailJourop;
    }


    @Override
    public void addJouropToList(Jourop jourop) {
        listeDetailJourop.add(jourop);
    }

    @Override
    public void createJouropFromList(List<String[]> listDetail) {
        for (String[] valeur: listDetail) {
            listeDetailJourop.add(new Jourop(valeur[0], valeur[1], valeur[2],valeur[6], valeur[3], valeur[9],Double.parseDouble(valeur[14]), valeur[15]));
        }
    }
}
