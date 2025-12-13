package com.company.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.company.user.dto.UserDTO;
import com.company.user.model.User;
import com.company.user.repository.UserRepository;
import com.company.user.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_ShouldSaveUser() {
        UserDTO dto = new UserDTO(null, "John", "john@example.com");
        User entity = new User(1L, "John", "john@example.com");

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(entity);

        UserDTO result = userService.createUser(dto);

        assertNotNull(result.getId());
        assertEquals("John", result.getName());
    }

    @Test
    void createUser_WhenEmailExists_ShouldThrowException() {
        UserDTO dto = new UserDTO(null, "John", "john@example.com");
        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.createUser(dto));
    }

    @Test
    void getUserById_WhenExists_ShouldReturnUser() {
        User entity = new User(1L, "John", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));

        UserDTO result = userService.getUserById(1L);
        assertEquals("John", result.getName());
    }

    @Test
    void deleteUser_ShouldCallRepository() {
        when(userRepository.existsById(1L)).thenReturn(true);
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }
}
