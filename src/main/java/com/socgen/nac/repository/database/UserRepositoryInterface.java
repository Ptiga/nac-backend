package com.socgen.nac.repository.database;

import com.socgen.nac.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends CrudRepository<User, String> {

}
