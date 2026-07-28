package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Book;

/**
 * Exercise 9: Spring Data JPA repository, giving CRUD operations on Book
 * for free (no implementation needed).
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
