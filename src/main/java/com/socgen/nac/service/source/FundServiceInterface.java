package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Fund;

import java.util.List;

public interface FundServiceInterface {

    List<Fund>getFundInformation();

    //List<Fund>getFundByTeam(String valuationTeam);
}
