package com.example.demo.controllers;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@RequestHeader("Access-Password")String key, @PathVariable("username") String username){
        if(!key.equals("akademiakodujestfajna")){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            return new ResponseEntity("User no exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user.get(), HttpStatus.OK);
    }

    @RequestMapping(value =  "/api/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody User user, @RequestHeader("Access-Password")String key) {
        if(!key.equals("akademiakodujestfajna")){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User userLocal = userRepository.findOne(user.getId());
        if(userLocal == null){
            return new ResponseEntity("User no exist", HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUser(@RequestBody User user, @RequestHeader("Access-Password")String key){
        if(!key.equals("akademiakodujestfajna")){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userLocal = userRepository.findByUsername(user.getUsername());
        if(userLocal.isPresent()){
            return new ResponseEntity("Username is not free", HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);

    }


}
