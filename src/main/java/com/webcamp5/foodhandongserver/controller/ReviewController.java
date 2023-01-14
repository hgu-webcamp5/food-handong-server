package com.webcamp5.foodhandongserver.controller;

import com.webcamp5.foodhandongserver.model.Review;
import com.webcamp5.foodhandongserver.model.request.ReviewCreationRequest;
import com.webcamp5.foodhandongserver.model.request.ReviewReadRequest;
import com.webcamp5.foodhandongserver.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review")
    public ResponseEntity readReviewsAll() {
        return ResponseEntity.ok(reviewService.readReviewsAll());
    }

    @GetMapping("/review/{restaurantId}")
    public ResponseEntity<List<ReviewReadRequest>> readReviewsRes(@PathVariable int restaurantId) {
        return ResponseEntity.ok(reviewService.readReviewsRes(restaurantId));
    }

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewCreationRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @PatchMapping("/review/{reviewId}")
    public ResponseEntity<Review> updateReview(@RequestBody ReviewCreationRequest request, @PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, request));
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }


}