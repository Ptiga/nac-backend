package com.mabanque.nac.repository.database;

import com.mabanque.nac.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends CrudRepository<User, String> {

}
