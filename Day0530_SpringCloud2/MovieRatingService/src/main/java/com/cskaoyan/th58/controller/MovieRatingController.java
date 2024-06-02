package com.cskaoyan.th58.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieRatingController {

    @GetMapping("/rating/{movieId}")
    public Map<String, Object> getMovieRating(@PathVariable String movieId) {
        Map<String, Object> rating = new HashMap<>();
        rating.put("movieId", movieId);
        rating.put("rating", 4.5);
        rating.put("reviews", 200);
        return rating;
    }
}