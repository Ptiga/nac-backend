package com.mabanque.nac.service.result;

import com.mabanque.nac.repository.database.ResultRepositoryInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



//(IT -> Integration Test)
@SpringBootTest
public class ResultServiceIT {

    @Autowired
    ResultRepositoryInterface resultRepository;


    @Test
    public void getResults(){
        Assertions.assertTrue(resultRepository.findAll().iterator().hasNext());
    }



}
