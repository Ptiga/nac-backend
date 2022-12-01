package com.mabanque.nac.service.check;


import com.mabanque.nac.entity.check.CheckFluctuationData;
import com.mabanque.nac.entity.source.Invcah;
import com.mabanque.nac.entity.source.Jourop;
import com.mabanque.nac.entity.source.Threshold;
import com.mabanque.nac.entity.source.Vinvca;

import java.util.List;

public interface CheckFluctuationServiceInterface {

    //Map<String, Double>getThresholds();

    //double retrieveTheshold(Map ThresholdList, String triComptable);

    boolean compareInvcahAndVinvca(Invcah invcah, Vinvca vinvca);

    boolean compareInvcahAndJourop(Invcah invcah, Jourop jourop);

    List<CheckFluctuationData> createCheckDataFromInvcahList(List<Invcah>listInvcah, List<Vinvca>listVinvca, List<Jourop>listJourop);

    CheckFluctuationData createCheckFluctuationData(Invcah invcah, List<Vinvca>listVinvca, List<Jourop>listJourop, List<Threshold> thresholds);

    boolean isInvcahUsableForCheck(Invcah invcah);

    Threshold searchThresholdByTriComptable(List<Threshold> thresholds, String triComptable);

    List<Threshold> createThresholds();

    //Iterable<Vinvca>


}
