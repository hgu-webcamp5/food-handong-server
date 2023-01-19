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

//    @GetMapping("/review")
//    public ResponseEntity readReviewsAll() {
//        return ResponseEntity.ok(reviewService.readReviewsAll());
//    }

    @GetMapping("/review/{restaurantId}")
    public ResponseEntity<List<ReviewReadRequest>> readReviewsRes(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(reviewService.readReviewsRes(restaurantId));
    }

    // 회원 ID로 해당 회원이 등록한 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity readReviewsAll(@RequestParam(required = false) String userId) {
        if (userId == null) {
            return ResponseEntity.ok(reviewService.readReviewsAll());
        }
        return ResponseEntity.ok(reviewService.readLikedReview(userId));
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
