package com.example.demo.services;

import com.example.demo.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookSearchService {

    static final String uri = "https://openlibrary.org/isbn/";
    static final String cacheUri = "http://localhost:8090/book-cache/";

    public String searchForBook(String isbn) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String cacheResult = restTemplate.getForObject(cacheUri+isbn,String.class);
        if(!cacheResult.equals("null")){
            return cacheResult;
        }

        String result = restTemplate.getForObject(uri+isbn+".json",String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(result);
        String title = node.get("title").asText();

        restTemplate.getForObject(cacheUri+isbn+"/"+title, String.class);
        return title;
    }
}
