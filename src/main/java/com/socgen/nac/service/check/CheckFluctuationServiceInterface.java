package com.socgen.nac.service.check;


import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;

import java.util.Map;

public interface CheckFluctuationServiceInterface {

    double calculateFluctuation(Vinvca vinvca, Invcah invcah);

    double retrieveTheshold(Map ThresholdList, String triComptable);

    boolean compareInvcahAndVinvca(Invcah invcah, Vinvca vinvca);

    boolean compareInvcahAndJourop(Invcah invcah, Jourop jourop);

    Object createCheckFluctuationData(Invcah invcah);



    //Iterable<Vinvca>


}
