package com.alw.app.controllers;

import com.alw.app.entities.User;
import com.alw.app.exceptions.NoUserFoundException;
import com.alw.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserController {

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create/user")
    public ResponseEntity<User> createUser (@RequestBody User user) throws NoUserFoundException {

        log.info(user.getUsername());
        log.info(user.getFirstname());
        log.info(user.getLastname());
        log.info(user.getEmail());
        log.info(user.getPassword());
        log.info(user.getAddress());

        User savedUser = userService.createUser(user);

        return new ResponseEntity (savedUser, HttpStatus.OK);

    }

    @PutMapping("/edit/user")
    public ResponseEntity<User> editUser (@RequestBody User user, @RequestParam long id) throws NoUserFoundException {
        user.setId(id);
        User updatedUser = userService.editUser(id);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<User> deleteUser(@RequestParam(name = "id") long id) throws NoUserFoundException {
        userService.deleteUser(id);
        return new ResponseEntity("User deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/get/all/users")
    public ResponseEntity<User> getAllUsers () {
        List<User> allUsers = userService.findAll();
        return new ResponseEntity(allUsers, HttpStatus.OK);
    }

    @GetMapping("/get/one/user")
    public ResponseEntity<User> getOneUser(@RequestParam long id) {
        Optional<User> oneUser = userService.findOneById(id);
        return new ResponseEntity(oneUser, HttpStatus.OK);
    }



}