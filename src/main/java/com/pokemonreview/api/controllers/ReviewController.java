package com.pokemonreview.api.controllers;


import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {


    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")

    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId) {

        return new ResponseEntity<>(reviewService.getReviewByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewsById(
            @PathVariable(value = "pokemonId")int pokemonId,
            @PathVariable(value = "id") int reviewId
    ){
        ReviewDto review = reviewService.getReviewById(pokemonId, reviewId);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }


    @PutMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable(value = "pokemonId")int pokemonId,
            @PathVariable(value = "id") int reviewId,
            @RequestBody ReviewDto reviewDto
    ){
       ReviewDto updateReview = reviewService.updateReview(pokemonId,reviewId,reviewDto);
       return new ResponseEntity<>(updateReview, HttpStatus.OK);

    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable(value = "pokemonId")int pokemonId,
            @PathVariable(value = "id") int reviewId
    ){
       reviewService.deleteReview(pokemonId, reviewId);
       return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);

    }
}
