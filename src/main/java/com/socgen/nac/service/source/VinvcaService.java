package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Vinvca;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinvcaService implements VinvcaServiceInterface {

    List<Vinvca> listeDetailVinvca = new ArrayList<>();


    @Override
    public void createVinvcaFromList(List<String[]> listDetail) {
        for (String[] valeur: listDetail) {
            listeDetailVinvca.add(new Vinvca(valeur[0], valeur[1], valeur[2],valeur[21], valeur[13], valeur[7],valeur[14], valeur[17], valeur[18],valeur[65], Double.parseDouble(valeur[24]), valeur[47]));
        }
    }


}
