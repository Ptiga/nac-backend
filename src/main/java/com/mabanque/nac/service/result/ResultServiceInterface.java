package com.mabanque.nac.service.result;

import com.mabanque.nac.entity.check.CheckFluctuationData;
import com.mabanque.nac.entity.result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ResultServiceInterface {

    List<Result> createResultFluctuationCheck(List<CheckFluctuationData> listeCheckFluctuation);

    List<Result> fromSourceFolderToResultList();

    List<Result> uploadResults();

    void saveNewResults(List<Result>results);

    List<Result> retrieveResults();

    ResponseEntity updateResult(String resultId, Result result);

    Optional<Result> getSelectedResult(String resultId);
}
