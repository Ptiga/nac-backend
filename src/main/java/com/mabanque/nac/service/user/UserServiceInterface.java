package com.mabanque.nac.service.user;

import com.mabanque.nac.entity.user.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;


public interface UserServiceInterface {

    List<User> getUsers();

    ResponseEntity addUser(User user);

    String getUserConnected(Principal principal);

    User getUser(String userName);
}
