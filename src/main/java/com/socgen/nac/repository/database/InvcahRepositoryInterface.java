package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Invcah;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvcahRepositoryInterface extends CrudRepository<Invcah, Long>  {
}