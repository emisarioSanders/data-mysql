package com.luis.datamysql.controller;

import com.luis.datamysql.repository.UserRepository;
import com.luis.datamysql.schema.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public @ResponseBody ResponseEntity<Optional<User>>  createUser(@RequestParam String name, @RequestParam String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        User u = userRepository.save(user);
        return new ResponseEntity<>(Optional.of(u), HttpStatus.OK) ;
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<User> findById(@PathVariable Integer id){

        return userRepository.findById(id);
    }
}
