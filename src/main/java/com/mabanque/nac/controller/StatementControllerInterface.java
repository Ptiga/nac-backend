package com.mabanque.nac.controller;

import org.springframework.http.ResponseEntity;

public interface StatementControllerInterface {

    ResponseEntity<String> checkNewStatements();

    ResponseEntity<String > uploadNewStatements();

}
