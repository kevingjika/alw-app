package com.alw.app.controllers;


import com.alw.app.entities.User;
import com.alw.app.repositories.UserRepository;
import com.alw.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class UserController {

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create/user")
    public ResponseEntity<User> createUser (@RequestBody User user) throws Exception {

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
    public ResponseEntity<User> editUser (@RequestBody User user, @RequestParam long id) throws Exception {
        user.setId(id);
        User updatedUser = userService.editUser(user);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<User> deleteUser(@RequestParam(name = "id") long id) throws Exception {
        userService.deleteUser(id);
        return new ResponseEntity("User deleted successfully.", HttpStatus.OK);
    }

}