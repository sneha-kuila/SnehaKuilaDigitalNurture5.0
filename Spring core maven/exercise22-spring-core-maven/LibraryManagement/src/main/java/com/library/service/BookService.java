package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.repository.BookRepository;

/**
 * Exercise 1: Basic service bean.
 * Exercise 2 & 7: Has both a constructor and a setter for BookRepository so it
 *                 can be wired via constructor injection OR setter injection,
 *                 depending on which is configured in applicationContext.xml.
 * Exercise 6: Annotated with @Service for component-scan based configuration.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    // Default constructor (needed for XML setter-injection style config)
    public BookService() {
    }

    // Exercise 7: Constructor injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Exercise 2 & 7: Setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName) {
        bookRepository.saveBook(bookName);
    }

    public String getBook(String bookName) {
        return bookRepository.findBook(bookName);
    }
}
