package com.example.reviewms.review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviews(Long companyId);
    Boolean AddReview(Long companyId, Review review);
    Review getReview(Long reviewId);
    Boolean updateReview(Long reviewId, Review review);
    Boolean deleteReview(Long reviewId);
}
