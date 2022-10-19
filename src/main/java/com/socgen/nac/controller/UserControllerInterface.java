package com.socgen.nac.controller;

import com.socgen.nac.entity.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserControllerInterface {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity addUser(@RequestBody User user);

}
