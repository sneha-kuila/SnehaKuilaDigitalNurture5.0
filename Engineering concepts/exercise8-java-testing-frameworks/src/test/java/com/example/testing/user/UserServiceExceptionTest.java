package com.example.testing.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/** Doc 4 - Exercise 6: Test Service Exception Handling */
@ExtendWith(MockitoExtension.class)
public class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserByIdOrThrow_shouldThrow_whenUserMissing() {
        when(userRepository.findById(404L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(404L));
    }
}
