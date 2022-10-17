package com.socgen.nac.controller;

import com.socgen.nac.entity.result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatementControllerInterface {

    ResponseEntity<String> checkNewStatements();

    ResponseEntity<String > uploadNewStatements();

    ResponseEntity<List<Result>> getCheckResult();

}
