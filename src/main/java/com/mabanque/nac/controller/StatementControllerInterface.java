package com.mabanque.nac.controller;

import org.springframework.http.ResponseEntity;

public interface StatementControllerInterface {

    ResponseEntity<String> checkNewStatements();

    ResponseEntity<String > uploadNewStatements();

    //ResponseEntity<List<Result>> getCheckResult();

   // ResponseEntity<Optional> getRequiredResult(@PathVariable("resultId") String resultId);

}
