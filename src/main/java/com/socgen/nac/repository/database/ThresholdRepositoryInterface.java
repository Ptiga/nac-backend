package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Threshold;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ThresholdRepositoryInterface extends CrudRepository<Threshold, String> {

    //@EntityGraph(value="nac.threshold", type= EntityGraph.EntityGraphType.FETCH)
    //@EntityGraph(value="nac.threshold", type= EntityGraph.EntityGraphType.LOAD)
    //Iterable<Threshold> findAll();

}
