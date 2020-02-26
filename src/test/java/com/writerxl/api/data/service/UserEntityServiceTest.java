package com.writerxl.api.data.service;

import com.writerxl.api.data.model.UserEntity;
import com.writerxl.api.data.repository.UserRepository;
import com.writerxl.api.exception.UserNotFoundException;
import com.writerxl.api.model.User;
import com.writerxl.api.model.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private static final String VALID_USER_KEY = "12345678";
    private static final String VALID_USER_EMAIL = "good@email.com";
    private static final String VALID_USER_FIRST_NAME = "Valid User";
    private static final String VALID_USER_LAST_NAME = "Valid User";
    private static final UserStatus VALID_USER_STATUS = UserStatus.ACTIVE;

    private static final String INVALID_USER_KEY = "XXXXXXXX";
    private static final String INVALID_USER_EMAIL = "invalid@email.com";

    private final UserEntity validUser = new UserEntity(
            VALID_USER_KEY,
            VALID_USER_EMAIL,
            VALID_USER_FIRST_NAME,
            VALID_USER_LAST_NAME,
            VALID_USER_STATUS
    );

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void whenGetByValidKey_thenReturnValidUser() {
        when(userRepository.findOneByUserKey(VALID_USER_KEY)).thenReturn(Optional.ofNullable(validUser));

        try {
            User returnedUser = userService.getUserByUserKey(VALID_USER_KEY);
            testValidUser(returnedUser);
        }
        catch (UserNotFoundException ex) {
            throw new RuntimeException("Unable to retrieve mock user by user key.");
        }
    }

    @Test
    public void whenGetByInvalidKey_thenThrowNotFoundException() {
        when(userRepository.findOneByUserKey(INVALID_USER_KEY)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByUserKey(INVALID_USER_KEY));
    }

    @Test
    public void whenFindByValidEmail_thenReturnValidUser() {
        when(userRepository.findOneByEmail(VALID_USER_EMAIL)).thenReturn(Optional.ofNullable(validUser));

        try {
            User returnedUser = userService.getUserByEmail(VALID_USER_EMAIL);
            testValidUser(returnedUser);
        }
        catch(UserNotFoundException ex) {
            throw new RuntimeException("Unable to retrieve mock user by valid email address.");
        }
    }

    @Test
    public void whenFindByInvalidUserEmail_thenThrowNotFoundException() {
        when(userRepository.findOneByEmail(INVALID_USER_EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(INVALID_USER_EMAIL));
    }

    private void testValidUser(User returnedUser) {
        assertEquals(VALID_USER_KEY, returnedUser.getUserKey());
        assertEquals(VALID_USER_EMAIL, returnedUser.getEmail());
        assertEquals(VALID_USER_FIRST_NAME, returnedUser.getFirstName());
        assertEquals(VALID_USER_LAST_NAME, returnedUser.getLastName());
        assertEquals(VALID_USER_STATUS, returnedUser.getStatus());
    }
}