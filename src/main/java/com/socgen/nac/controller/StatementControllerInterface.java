package com.socgen.nac.controller;

import com.socgen.nac.entity.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface StatementControllerInterface {

    ResponseEntity<String> checkNewStatements();

    ResponseEntity<String > uploadNewStatements();

    ResponseEntity<List<Result>> getCheckResult();

    ResponseEntity<Optional> getRequiredResult(@PathVariable("resultId") String resultId);

}
