package com.socgen.nac.repository.database;

import com.socgen.nac.entity.result.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepositoryInterface extends CrudRepository<Result, Long> {


}
