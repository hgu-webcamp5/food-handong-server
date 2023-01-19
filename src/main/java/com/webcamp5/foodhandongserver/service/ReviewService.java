package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.Review;
import com.webcamp5.foodhandongserver.model.request.ReviewCreationRequest;
import com.webcamp5.foodhandongserver.model.request.ReviewReadRequest;
import com.webcamp5.foodhandongserver.repository.LikeRepository;
import com.webcamp5.foodhandongserver.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;

    public List<Review> readReviewsAll() {
        return reviewRepository.findAll();
    }

    public List<ReviewReadRequest> readReviewsRes(Long restaurantId) {
        List<Review> reviews = reviewRepository.findAllByRestaurantId(restaurantId);

        if(!reviews.isEmpty()) {
            List<ReviewReadRequest> result = new ArrayList<>();
            reviews.forEach(review -> {
                ReviewReadRequest reviewObj = new ReviewReadRequest();
                BeanUtils.copyProperties(review,reviewObj);

                reviewObj.setUserName(userService.readUser(review.getUserId()).getName());
                reviewObj.setProfileUrl(userService.readUser(review.getUserId()).getProfileUrl());
                result.add(reviewObj);
            });
            return result;
        }
        throw new EntityNotFoundException("Can't find any review under given restaurantId");
    }
    // 회원 ID로 해당 회원이 등록한 리뷰 조회
    public List<ReviewReadRequest> readLikedReview(String userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);

        if(!reviews.isEmpty()) {
            List<ReviewReadRequest> result = new ArrayList<>();
            reviews.forEach(review -> {
                ReviewReadRequest reviewObj = new ReviewReadRequest();
                BeanUtils.copyProperties(review,reviewObj);

                reviewObj.setUserName(userService.readUser(review.getUserId()).getName());
                reviewObj.setProfileUrl(userService.readUser(review.getUserId()).getProfileUrl());
                result.add(reviewObj);
            });
            return result;
        }
        throw new EntityNotFoundException("Can't find any review under given userId");
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
