package com.writerxl.api.service;

import com.writerxl.api.model.User;

public interface UserService {

    User getUserByUserKey(String userKey);

    User getUserByEmailAddress(String emailAddress);

    User createUser(User user);

}
