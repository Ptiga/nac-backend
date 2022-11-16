package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Vinvca;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinvcaRepositoryInterface extends CrudRepository<Vinvca, Long> {
}
