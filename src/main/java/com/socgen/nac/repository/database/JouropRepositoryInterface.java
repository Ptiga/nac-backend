package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Jourop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JouropRepositoryInterface extends CrudRepository<Jourop, Long> {
}