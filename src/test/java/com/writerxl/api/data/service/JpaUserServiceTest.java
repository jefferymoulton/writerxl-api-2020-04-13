package com.writerxl.api.data.service;

import com.writerxl.api.data.model.JpaUser;
import com.writerxl.api.data.repository.JpaUserRepository;
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
public class JpaUserServiceTest {

    private JpaUserService userService;

    @Mock
    private JpaUserRepository mockRepository;

    private JpaUser validUser = new JpaUser("12345678",
            "good@email.com",
            "Valid",
            "User",
            UserStatus.ACTIVE);

    private JpaUser invalidUser = new JpaUser("XXXXXXXX",
            "invalid@email.com",
            "Invalid",
            "User",
            UserStatus.DELETED);

    @BeforeEach
    public void setUp() {
        userService = new JpaUserService(mockRepository);
    }

    @Test
    public void whenGetByValidUserKey_thenReturnValidUser() {
        when(mockRepository.findOneByUserKey(validUser.getUserKey())).thenReturn(Optional.ofNullable(validUser));

        try {
            User returnedUser = userService.getUserByUserKey(validUser.getUserKey());
            testValidUser(validUser, returnedUser);
        }
        catch (UserNotFoundException ex) {
            throw new RuntimeException("Unable to retrieve mock user by user key.");
        }
    }

    @Test
    public void whenFindByInvalidUserKey_thenThrowNotFoundException() {
        when(mockRepository.findOneByUserKey(invalidUser.getUserKey())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByUserKey(invalidUser.getUserKey()));
    }

    @Test
    public void whenFindByValidEmail_thenReturnValidUser() {
        when(mockRepository.findOneByEmail(validUser.getEmail())).thenReturn(Optional.ofNullable(validUser));

        try {
            User returnedUser = userService.getUserByEmail(validUser.getEmail());
            testValidUser(validUser, returnedUser);
        }
        catch(UserNotFoundException ex) {
            throw new RuntimeException("Unable to retrieve mock user by valid email address.");
        }
    }

    @Test
    public void whenFindByInvalidUserEmail_thenThrowNotFoundException() {
        when(mockRepository.findOneByEmail(invalidUser.getEmail())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(invalidUser.getEmail()));
    }

    private void testValidUser(JpaUser validUser, User returnedUser) {
        assertEquals(validUser.getUserKey(), returnedUser.getUserKey());
        assertEquals(validUser.getEmail(), returnedUser.getEmail());
        assertEquals(validUser.getFirstName(), returnedUser.getFirstName());
        assertEquals(validUser.getLastName(), returnedUser.getLastName());
        assertEquals(validUser.getStatus(), returnedUser.getStatus());
    }
}