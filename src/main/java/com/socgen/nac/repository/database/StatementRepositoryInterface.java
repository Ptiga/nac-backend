package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Statement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepositoryInterface extends CrudRepository<Statement, String> {

}
