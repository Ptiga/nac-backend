package com.mabanque.nac.service.user;


import com.mabanque.nac.entity.user.User;
import com.mabanque.nac.jwt.JwtController;
import com.mabanque.nac.jwt.JwtFilter;
import com.mabanque.nac.jwt.JwtUtils;
import com.mabanque.nac.repository.database.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
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

    @Autowired
    JwtController jwtController;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter jwtFilter;

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

    private User prepareUserDataBeforeSave(User user){
        User userToSave = new User();
        userToSave.setLogin(user.getLogin());
        userToSave.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userToSave.setFirstName(StringUtils.capitalize((user.getFirstName())));
        userToSave.setLastName(user.getLastName().toUpperCase());
        userToSave.setRole(user.getRole().toUpperCase());
        userToSave.setTeam(user.getTeam().toUpperCase());
        return userToSave;
    }

    @Override
    public ResponseEntity addUser(User user) {
        Optional<User> userCheck = userRepository.findById(user.getLogin());
        //if(userCheck != null){
        if(!userCheck.isEmpty()){
            return new ResponseEntity("Utilisateur déjà présent en base", HttpStatus.BAD_REQUEST);
        }
        User userSaved = userRepository.save(prepareUserDataBeforeSave(user));
        Authentication authentication = jwtController.logUser(user.getLogin(), user.getPassword());
        //On génère le token
        String jwt = jwtUtils.generateToken(authentication);
        //J'ajoute le token dans le header (qui sera envoyé au front (client)
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(jwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);


        return new ResponseEntity(userSaved, HttpStatus.CREATED);
    }


    @Override
    public String getUserConnected(Principal principal) {
        if(!(principal instanceof UsernamePasswordAuthenticationToken)){
            throw new RuntimeException("Utilisateur non trouvé");
        }
        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
        //On va vérifier que l'utilisateur existe en base
        Optional<User> connectedUser = userRepository.findById(user.getName());
        //On renvoie son login
        return connectedUser.get().getLogin();
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findById(userName).get();
    }
}
