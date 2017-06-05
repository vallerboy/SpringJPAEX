package com.example.demo.controllers;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by OskarPraca on 2017-06-05.
 */

@Controller
public class ApiController {

    @Autowired
    UserRepository userRepository;

    //localhost:8080/api/user
    @RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity getAllUser() {
        Iterable<User> users  = userRepository.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

}
