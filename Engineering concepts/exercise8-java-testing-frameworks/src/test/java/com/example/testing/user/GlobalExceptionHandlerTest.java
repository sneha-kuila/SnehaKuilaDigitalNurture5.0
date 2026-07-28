package com.example.testing.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Doc 4 - Exercise 8: Test Controller Exception Handling (@ControllerAdvice) */
@WebMvcTest(UserController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserStrict_shouldReturn404_whenUserNotFound() throws Exception {
        when(userService.getUserByIdOrThrow(404L))
                .thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/strict/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
