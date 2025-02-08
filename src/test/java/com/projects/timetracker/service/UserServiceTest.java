package com.projects.timetracker.service;

import com.projects.timetracker.entity.User;
import com.projects.timetracker.exception.UserNotFoundException;
import com.projects.timetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_Success() {
        userService.createUser("TestUser");

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void updateUser_Success() {
        User user = new User();
        user.setId(1L);
        user.setName("TestUser");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.updateUser(1L, "NewName");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void updateUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, "NewName"));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void deleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_UserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(1)).existsById(1L);
    }
}

