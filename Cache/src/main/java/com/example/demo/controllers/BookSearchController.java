package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.services.BookManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookSearchController {

    @Autowired
    BookManager service;

    @GetMapping("/book-cache/{isbn}")
    public ResponseEntity<String> getBookName(@PathVariable("isbn") String isbn) throws JsonProcessingException {
        return new ResponseEntity<>(service.loadBook(isbn), HttpStatus.OK);
    }

    @GetMapping("/book-cache/{isbn}/{title}")
    public ResponseEntity<String> addBookName(@PathVariable("isbn") String isbn,@PathVariable("title") String title) {
        return new ResponseEntity<>(service.addBookToCache(isbn, title), HttpStatus.OK);
    }
}
