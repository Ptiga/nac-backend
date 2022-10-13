package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;

import java.util.List;

public interface ResultServiceInterface {

    public List<Result> getResultList();

    void createResultFluctuationCheck(List<CheckFluctuationData> listeCheckFluctuation);

    void fromSourceFolderToResultList();

}
