package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BookManager {

    @Autowired
    Cache<String, String> cache;

    @Autowired
    BookRepository repository;

    static final String uri = "https://openlibrary.org/isbn/";

    public String addBookToCache(String isbn,String title){
        cache.put(isbn, title);
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        repository.save(book);
        return "success";
    }

    public String loadBook(String isbn) throws JsonProcessingException {
        if(cache.getIfPresent(isbn) == null){
            Optional<Book> bookOptional = repository.findById(isbn);
            if(!bookOptional.isEmpty()){
                cache.put(isbn,bookOptional.get().getTitle());
                return bookOptional.get().getTitle();
            }
            return "null";
        }
        return cache.getIfPresent(isbn);
    }
}
