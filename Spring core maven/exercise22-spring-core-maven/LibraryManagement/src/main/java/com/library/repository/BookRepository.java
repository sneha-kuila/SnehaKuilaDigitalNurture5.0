package com.library.repository;

import org.springframework.stereotype.Repository;

/**
 * Exercise 1: Basic repository bean.
 * Exercise 6: Annotated with @Repository so it can be picked up by component scanning.
 */
@Repository
public class BookRepository {

    public void saveBook(String bookName) {
        System.out.println("Book saved to repository: " + bookName);
    }

    public String findBook(String bookName) {
        System.out.println("Fetching book from repository: " + bookName);
        return bookName;
    }
}
