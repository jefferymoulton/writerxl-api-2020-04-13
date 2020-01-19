package com.writerxl.api.service;

import com.writerxl.api.exception.UserAlreadyExistsException;
import com.writerxl.api.exception.UserNotFoundException;
import com.writerxl.api.model.User;

public interface UserService {

    User getUserByUserKey(String userKey) throws UserNotFoundException;

    User getUserByEmail(String email) throws UserNotFoundException;

    User saveUser(User user) throws UserAlreadyExistsException;

}
