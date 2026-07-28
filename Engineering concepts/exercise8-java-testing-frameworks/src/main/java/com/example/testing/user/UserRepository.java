package com.example.testing.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // Doc 4 - Exercise 7: custom query method
    List<User> findByName(String name);
}
