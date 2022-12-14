package com.mabanque.nac.controller;

import com.mabanque.nac.entity.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserControllerInterface {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity addUser(@RequestBody User user);

    ResponseEntity isUserConnected();

}
