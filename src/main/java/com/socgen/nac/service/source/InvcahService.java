package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Statement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvcahService implements InvcahServiceInterface {

    List<Invcah> listeDetailInvcah = new ArrayList<>();


    @Override
    public void createInvcahFromList(List<String[]> listDetail) {
        for (String[] valeur: listDetail) {
            listeDetailInvcah.add(new Invcah(valeur[0], valeur[1], valeur[2],valeur[21], valeur[13], valeur[7],valeur[14], valeur[17], valeur[18],valeur[65], Double.parseDouble(valeur[24]), valeur[47]));
        }
    }
}
