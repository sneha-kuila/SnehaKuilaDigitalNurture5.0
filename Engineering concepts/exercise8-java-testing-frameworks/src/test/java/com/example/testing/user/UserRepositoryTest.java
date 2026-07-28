package com.example.testing.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Doc 4 - Exercise 7: Test Custom Repository Query (findByName) */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName_shouldReturnMatchingUsers() {
        userRepository.save(new User(1L, "Diana"));
        userRepository.save(new User(2L, "Diana"));
        userRepository.save(new User(3L, "Eve"));

        List<User> results = userRepository.findByName("Diana");

        assertEquals(2, results.size());
    }
}
