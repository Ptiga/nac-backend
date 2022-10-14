package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Threshold;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ThresholdRepositoryTest {

    @Autowired
    private ThresholdRepositoryInterface thresholdRepository;

    public ThresholdRepositoryInterface getThresholdRepository() {
        return thresholdRepository;
    }

    public void setThresholdRepository(ThresholdRepositoryInterface thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }



    @Test
    public void retrieveAllData(){
        Iterable<Threshold>thresholds=thresholdRepository.findAll();
        for (Threshold t: thresholds) {
            System.out.println(t.getTriComptable() + " || " + t.getTypeInstrument() + " || " + t.getThreshold());
        }
    }

}
