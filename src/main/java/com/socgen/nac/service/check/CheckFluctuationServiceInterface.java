package com.socgen.nac.service.check;


import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;

import java.util.List;
import java.util.Map;

public interface CheckFluctuationServiceInterface {

    Map<String, Double>getThresholds();

    double retrieveTheshold(Map ThresholdList, String triComptable);

    boolean compareInvcahAndVinvca(Invcah invcah, Vinvca vinvca);

    boolean compareInvcahAndJourop(Invcah invcah, Jourop jourop);

    List<CheckFluctuationData> createCheckDataFromInvcahList(List<Invcah>listInvcah, List<Vinvca>listVinvca, List<Jourop>listJourop);

    CheckFluctuationData createCheckFluctuationData(Invcah invcah, List<Vinvca>listVinvca, List<Jourop>listJourop);

    boolean isInvcahUsableForCheck(Invcah invcah);

    //Iterable<Vinvca>


}
