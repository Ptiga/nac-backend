package com.mabanque.nac.repository.database;

import com.mabanque.nac.entity.source.Invcah;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvcahRepositoryInterface extends CrudRepository<Invcah, Long>  {
}
