package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JouropService implements JouropServiceInterface{


    @Override
    public List<Jourop> createJouropAndAddToList(List<String[]> listDetail) {
        List<Jourop> listeDetailJourop = new ArrayList<>();
        for (String[] valeur: listDetail) {
            if(valeur[0].substring(0,6).equals("jourop")) {
                listeDetailJourop.add(new Jourop(valeur[0], valeur[1], valeur[2],valeur[6], valeur[3], valeur[9],Double.parseDouble(valeur[14]), valeur[15]));
            }
        }
        return listeDetailJourop;
    }
}
