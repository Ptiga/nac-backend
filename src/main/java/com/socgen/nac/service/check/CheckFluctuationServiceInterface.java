package com.socgen.nac.service.check;


import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;

import java.util.List;
import java.util.Map;

public interface CheckFluctuationServiceInterface {

    List<CheckFluctuationData> getListeCheckFluctuation();

    Map<String, Double>getThresholds();

    //double calculateFluctuation(double coursJour, double coursVeille);

    double retrieveTheshold(Map ThresholdList, String triComptable);

    boolean compareInvcahAndVinvca(Invcah invcah, Vinvca vinvca);

    boolean compareInvcahAndJourop(Invcah invcah, Jourop jourop);

    void createCheckFluctuationData(Invcah invcah);

    boolean isInvcahUsableForCheck(Invcah invcah);


    //Iterable<Vinvca>


}
