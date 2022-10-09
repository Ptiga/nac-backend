package com.socgen.nac.service.check;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckFluctuationService implements CheckFluctuationServiceInterface{

    @Override
    public CheckFluctuationData createCheckFluctuationData(Invcah invcah, Vinvca vinvca, Jourop jourop) {
        return null;
    }

    @Override
    public double calculateFluctuation(Vinvca vinvca, Invcah invcah) {
        return Math.abs((invcah.getCours()-vinvca.getCours())/vinvca.getCours());
    }

    @Override
    public double retrieveTheshold(Map thresholdList, String triComptable) {
        return (double) thresholdList.get(triComptable);
    }
}
