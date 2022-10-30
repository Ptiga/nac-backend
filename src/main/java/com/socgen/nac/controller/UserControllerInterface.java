package com.socgen.nac.controller;

import com.socgen.nac.entity.user.User;
import com.socgen.nac.jwt.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserControllerInterface {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity addUser(@RequestBody User user);

    ResponseEntity isUserConnected();

}
