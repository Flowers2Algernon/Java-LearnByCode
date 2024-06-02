package com.cskaoyan.th58.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieInfoController {

    @GetMapping("/movie/{movieId}")
    public Map<String, String> getMovieInfo(@PathVariable String movieId) {
        Map<String, String> movie = new HashMap<>();
        movie.put("movieId", movieId);
        movie.put("title", "The Matrix");
        movie.put("description", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.");
        return movie;
    }
}