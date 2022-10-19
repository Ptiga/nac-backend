package com.socgen.nac.service.user;


import com.socgen.nac.entity.user.User;
import com.socgen.nac.repository.database.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity addUser(User user) {
        Optional<User> userCheck = userRepository.findById(user.getLogin());
        if(userCheck != null){
            return new ResponseEntity("Utilisateur déjà présent en base", HttpStatus.BAD_REQUEST);
        }
        User userSaved = userRepository.save(user);
        return new ResponseEntity(userSaved, HttpStatus.CREATED);
    }
}
