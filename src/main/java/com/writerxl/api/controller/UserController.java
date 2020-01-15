package com.writerxl.api.controller;

import com.writerxl.api.exception.UserAlreadyExistsException;
import com.writerxl.api.exception.UserNotFoundException;
import com.writerxl.api.exception.WriterXLException;
import com.writerxl.api.model.User;
import com.writerxl.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/key/{userKey}", produces = "application/json")
    public User getUserByUserKey(@PathVariable String userKey) {
        try {
            return userService.getUserByUserKey(userKey);
        }
        catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user with key: " + userKey);
        }
    }

    @GetMapping(path = "/email/{emailAddress:.+}", produces = "application/json")
    public User getUserByEmailAddress(@PathVariable String emailAddress) {
        try {
            return userService.getUserByEmailAddress(emailAddress);
        }
        catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find user with email address: " + emailAddress);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        try {
            return userService.createUser(user);
        }
        catch (UserAlreadyExistsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email already exists.", ex);
        }
        catch (WriterXLException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user.", ex);
        }
    }

}
