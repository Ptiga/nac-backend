package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepositoryInterface extends CrudRepository<Fund, String> {

    //Iterable<Fund> findbyteam(String valuationTeam);
    //Iterable<Fund>findFundByTeam(String valuationTeam);
}
