package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Fund;
import org.springframework.data.repository.CrudRepository;

public interface FundRepositoryInterface extends CrudRepository<Fund, String> {
}
