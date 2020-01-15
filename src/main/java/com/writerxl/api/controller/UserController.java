package com.writerxl.api.controller;

import com.writerxl.api.exception.UserAlreadyExistsException;
import com.writerxl.api.exception.UserNotFoundException;
import com.writerxl.api.exception.WriterXLException;
import com.writerxl.api.model.User;
import com.writerxl.api.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Api(value = "/user")
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Retrieve a user by key.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found."),
            @ApiResponse(code = 404, message = "No matching user was found.")
    })
    @GetMapping(path = "/key/{userKey}", produces = "application/json")
    public User getUserByUserKey(@ApiParam("User key") @PathVariable String userKey) {
        try {
            return userService.getUserByUserKey(userKey);
        }
        catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user with key: " + userKey);
        }
    }

    @ApiOperation(value = "Retrieve user by email address.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found."),
            @ApiResponse(code = 404, message = "No matching user was found.")
    })
    @GetMapping(path = "/email/{email:.+}", produces = "application/json")
    public User getUserByEmail(@ApiParam("Email address") @PathVariable String email) {
        try {
            return userService.getUserByEmail(email);
        }
        catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find user with email address: " + email);
        }
    }

    @ApiOperation(value = "Retrieve user by email address.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created."),
            @ApiResponse(code = 403, message = "User with that email address already exists."),
            @ApiResponse(code = 500, message = "Unable to create user due to error.")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        try {
            return userService.createUser(user);
        }
        catch (UserAlreadyExistsException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
    }

}
