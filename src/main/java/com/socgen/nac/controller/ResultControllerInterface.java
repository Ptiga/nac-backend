package com.socgen.nac.controller;

import com.socgen.nac.entity.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ResultControllerInterface {

    ResponseEntity<List<Result>> getCheckResult();

    ResponseEntity<Optional> getRequiredResult(@PathVariable("resultId") String resultId);

    ResponseEntity updateResult(@PathVariable("resultId") String resultId, @RequestBody Result result);
}
