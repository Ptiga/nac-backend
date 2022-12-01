package com.mabanque.nac.repository.database;

import com.mabanque.nac.entity.source.Jourop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JouropRepositoryInterface extends CrudRepository<Jourop, Long> {
}
