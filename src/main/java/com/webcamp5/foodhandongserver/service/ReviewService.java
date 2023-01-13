package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.Review;
import com.webcamp5.foodhandongserver.model.request.ReviewCreationRequest;
import com.webcamp5.foodhandongserver.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> readReviewsAll() {
        return reviewRepository.findAll();
    }

    public List<Review> readReviewsRes(int restaurantId) {
        List<Review> reviews = reviewRepository.findAllByRestaurantId(restaurantId);
        if(!reviews.isEmpty()) {
            return reviews;
        }
        throw new EntityNotFoundException("Can't find any review under given restaurantId");
    }

    public Review createReview(ReviewCreationRequest review) {
        Review reviewToCreate = new Review();
        BeanUtils.copyProperties(review, reviewToCreate);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        reviewToCreate.setCreatedTime(timestamp);
        return reviewRepository.save(reviewToCreate);
    }

    public Review updateReview(Long reviewId, ReviewCreationRequest request) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(!optionalReview.isPresent()) {
            throw new EntityNotFoundException("Review not present in the database");
        }

        Review review = optionalReview.get();
        review.setReview(request.getReview());
        review.setRating(request.getRating());
//        review.setModifiedTime(request.getModifiedTime());
        return reviewRepository.save(review);
    }


    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }


}
