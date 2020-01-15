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
    public User getUserByUserKey(String userKey) {
        Optional<JpaUser> user = userRepository.findOneByUserKey(userKey);
        return JpaUserMapper.MAPPER.map(user.orElseThrow(UserNotFoundException::new));
    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {
        Optional<JpaUser> user = userRepository.findOneByEmailAddress(emailAddress);
        return JpaUserMapper.MAPPER.map(user.orElseThrow(UserNotFoundException::new));
    }

    @Override
    public User createUser(User user) {
        try {
            if (getUserByEmailAddress(user.getEmailAddress()) != null) {
                throw new UserAlreadyExistsException();
            }
        } catch (UserNotFoundException ignored) {
            // Do nothing
        }

        JpaUser saveUser = JpaUserMapper.MAPPER.mapDb(user);

        return JpaUserMapper.MAPPER.map(userRepository.save(saveUser));
    }

}
