package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Fund;
import com.socgen.nac.repository.database.FundRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundService implements FundServiceInterface{

    @Autowired
    FundRepositoryInterface fundRepository;

    public FundRepositoryInterface getVinvcaRepository() {
        return fundRepository;
    }

    public void setFundRepository(FundRepositoryInterface fundRepository) {
        this.fundRepository = fundRepository;
    }


    public FundService(FundRepositoryInterface fundRepository){
        this.fundRepository = fundRepository;
    }

    
    @Override
    public List<Fund> getFundInformation() {
        List<Fund>fundInformation = new ArrayList<>();
        Iterable<Fund> fundFromDatabase = fundRepository.findAll();
        fundFromDatabase.forEach(fundInformation::add);
        return fundInformation;
    }
/*
    @Override
    public List<Fund> getFundByTeam(String valuationTeam) {
        List<Fund>fundInformation = new ArrayList<>();
        Iterable<Fund> fundFromDatabase = fundRepository.findFundByTeam(valuationTeam);
        fundFromDatabase.forEach(fundInformation::add);
        return fundInformation;
    }
*/

}
