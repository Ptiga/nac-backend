package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultService implements ResultServiceInterface {

    private List<Result> resultList = new ArrayList<>();

    public List<Result> getResultList() {
        return resultList;
    }

    @Override
    public void createResultFluctuationCheck(List<CheckFluctuationData> listeCheckFluctuation) {
        for (CheckFluctuationData checkFluctuationData: listeCheckFluctuation) {
            Result checkResult = new Result(checkFluctuationData.getInvcah(), checkFluctuationData.getFluctuation(), checkFluctuationData.getThreshold(), checkFluctuationData.getAlertType());
            if(isFluctuationDataContainsVinvca(checkFluctuationData)){
                checkResult.addVinvcaAttributes(checkFluctuationData.getVinvca());
            }else{
                checkResult.addJouropAttributes(checkFluctuationData.getJourop());
            }
            resultList.add(checkResult);
        }
            }




    private boolean isFluctuationDataContainsVinvca(CheckFluctuationData checkFluctuationData) {
        if (checkFluctuationData.getVinvca()!=null){
            return true;
        }else{
            return false;
        }
    }
}
