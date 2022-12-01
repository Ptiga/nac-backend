package com.mabanque.nac.repository.database;

import com.mabanque.nac.entity.source.Threshold;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThresholdRepositoryInterface extends CrudRepository<Threshold, String> {

    //@EntityGraph(value="nac.threshold", type= EntityGraph.EntityGraphType.FETCH)
    //@EntityGraph(value="nac.threshold", type= EntityGraph.EntityGraphType.LOAD)
    //Iterable<Threshold> findAll();

}
