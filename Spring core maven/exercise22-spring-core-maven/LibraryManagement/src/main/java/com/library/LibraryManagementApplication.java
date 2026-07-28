package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.service.BookService;

/**
 * Loads the Spring IoC container from applicationContext.xml and exercises
 * the BookService bean, so you can see dependency injection, component
 * scanning, and AOP logging all working together (Exercises 1, 2, 3, 5, 6, 7).
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean(BookService.class);

        bookService.addBook("Effective Java");
        bookService.getBook("Effective Java");
    }
}
