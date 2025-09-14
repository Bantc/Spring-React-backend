package com.bart.movies.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bart.movies.data.Review;
import com.bart.movies.service.ReviewService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    
    @PostMapping
    public ResponseEntity<Review> postReview(@RequestBody Map<String, String> reqBody) {
        return new ResponseEntity<Review>(reviewService.createReview(reqBody.get("reviewBody"), reqBody.get("imdbId")), HttpStatus.CREATED);
    }
}
