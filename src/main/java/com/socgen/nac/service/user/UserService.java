package com.socgen.nac.service.user;


import com.socgen.nac.entity.user.User;
import com.socgen.nac.repository.database.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepositoryInterface userRepository;

    public UserRepositoryInterface getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public UserService(UserRepositoryInterface userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User>users = new ArrayList<>();
        Iterable<User> usersFromDatabase = userRepository.findAll();
        usersFromDatabase.forEach(users::add);
        return users;
    }
}
