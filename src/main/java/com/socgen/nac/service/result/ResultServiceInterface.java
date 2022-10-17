package com.socgen.nac.service.result;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.result.Result;

import java.util.List;

public interface ResultServiceInterface {

    List<Result> createResultFluctuationCheck(List<CheckFluctuationData> listeCheckFluctuation);

    List<Result> fromSourceFolderToResultList();

    List<Result> uploadResults();

    void saveNewResults(List<Result>results, List<Result>uploadedResults);

}
