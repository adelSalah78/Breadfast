package com.example.demo.controllers;

import com.example.demo.services.BookSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookSearchController {

    @Autowired
    BookSearchService service;

    @GetMapping("/book/{isbn}")
    public ResponseEntity<String> getBookName(@PathVariable("isbn") String isbn) throws JsonProcessingException {
        return new ResponseEntity<>(service.searchForBook(isbn), HttpStatus.OK);
    }
}
