package com.writerxl.api.data.service;

import com.writerxl.api.data.mapper.UserMapper;
import com.writerxl.api.data.model.UserEntity;
import com.writerxl.api.data.repository.UserRepository;
import com.writerxl.api.exception.UserAlreadyExistsException;
import com.writerxl.api.exception.UserNotFoundException;
import com.writerxl.api.model.User;
import com.writerxl.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User getUserByUserKey(String userKey) throws UserNotFoundException {
        return UserMapper.MAPPER.toModel(findUserByUserKey(userKey).orElseThrow(
                () -> new UserNotFoundException("Unable to find user with the specified key.")
        ));
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        return UserMapper.MAPPER.toModel(findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException("Unable to find user with the specified email address.")
        ));
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with that email address already exists.");
        }
        else if (findUserByUserKey(user.getUserKey()).isPresent()) {
            throw new UserAlreadyExistsException("User with that key already exists.");
        }

        UserEntity saveUser = UserMapper.MAPPER.mapEntity(user);

        return UserMapper.MAPPER.toModel(userRepository.save(saveUser));
    }

    private Optional<UserEntity> findUserByUserKey(String userKey) {
        return userRepository.findOneByUserKey(userKey);
    }

    private Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

}
