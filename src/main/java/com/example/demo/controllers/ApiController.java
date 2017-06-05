package com.example.demo.controllers;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * Created by OskarPraca on 2017-06-05.
 */

@Controller
public class ApiController {

    @Autowired
    UserRepository userRepository;

    //localhost:8080/api/user
    @RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUser(@RequestHeader("Access-Password") String key) {

        if(!key.equals("akademiakodujestfajna")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Iterable<User> users  = userRepository.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/user/{username}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity getUser(@RequestHeader("Access-Password") String key,
                                  @PathVariable("username") String username){

        if(!key.equals("akademiakodujestfajna")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()) {
            return new ResponseEntity("User no exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user.get(), HttpStatus.OK);

    }



}
