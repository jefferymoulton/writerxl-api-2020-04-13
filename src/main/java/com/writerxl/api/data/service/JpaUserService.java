package com.writerxl.api.data.service;

import com.writerxl.api.data.mapper.JpaUserMapper;
import com.writerxl.api.data.model.JpaUser;
import com.writerxl.api.data.repository.JpaUserRepository;
import com.writerxl.api.exception.UserAlreadyExistsException;
import com.writerxl.api.exception.UserNotFoundException;
import com.writerxl.api.model.User;
import com.writerxl.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    private JpaUserRepository userRepository;

    @Autowired
    public JpaUserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUserKey(String userKey) throws UserNotFoundException {
        return JpaUserMapper.MAPPER.map(findUserByUserKey(userKey).orElseThrow(
                () -> new UserNotFoundException("Unable to find user with the specified key.")
        ));
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        return JpaUserMapper.MAPPER.map(findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException("Unable to find user with the specified email address.")
        ));
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with that email address already exists.");
        }
        else if (findUserByUserKey(user.getUserKey()).isPresent()) {
            throw new UserAlreadyExistsException("User with that key already exists.");
        }

        JpaUser saveUser = JpaUserMapper.MAPPER.mapDb(user);

        return JpaUserMapper.MAPPER.map(userRepository.save(saveUser));
    }

    private Optional<JpaUser> findUserByUserKey(String userKey) {
        return userRepository.findOneByUserKey(userKey);
    }

    private Optional<JpaUser> findUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

}
