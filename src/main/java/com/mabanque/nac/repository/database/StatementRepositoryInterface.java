package com.mabanque.nac.repository.database;

import com.mabanque.nac.entity.source.Statement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepositoryInterface extends CrudRepository<Statement, String> {

}
