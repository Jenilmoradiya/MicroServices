package com.example.reviewms.review.impl;

import com.example.reviewms.review.Review;
import com.example.reviewms.review.ReviewRepository;
import com.example.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplReviewService implements ReviewService {

    private final ReviewRepository reviewRepository;
//    private final CompanyService companyService;
//    private final CompanyRepository companyRepository;

    public ImplReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean AddReview(Long companyId, Review review) {
//        Company company = companyService.GetCompanyById(companyId);

        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }

    }

//    @Override
//    public Review getReview(Long companyId, Long reviewId) {
//       List<Review> reviews= reviewRepository.findByCompanyId(companyId);
//        return reviews.stream()
//                .filter(review -> review.getId().equals(reviewId))
//                .findFirst()
//                .orElse(null);
//    }
@Override
public Review getReview(Long reviewId) {
    return reviewRepository.findById(reviewId).orElse(null);
}

//    @Override
//    public Boolean updateReview(Long companyId, Long reviewId, Review updatedreview) {
//        if(companyService.GetCompanyById(companyId)!=null){
//            updatedreview.setCompany(companyService.GetCompanyById(companyId));
//            updatedreview.setId(reviewId);
//            reviewRepository.save(updatedreview);
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
    @Override
    public Boolean updateReview(Long reviewId, Review updatedreview) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedreview.getTitle());
            review.setDescription(updatedreview.getDescription());
            review.setRating(updatedreview.getRating());
            review.setCompanyId(updatedreview.getCompanyId());
            reviewRepository.save(updatedreview);
            return true;
        }
        else {
            return false;
        }
    }

//    @Override
//    public Boolean deleteReview(Long companyId, Long reviewId) {
//        if(companyService.GetCompanyById(companyId)!=null &&
//        reviewRepository.existsById(reviewId)){
//            Review review = reviewRepository.findById(reviewId).orElse(null);
//            Company company=review.getCompany();
//            company.getReviews().remove(review);
//            review.setCompany(null);
//            reviewRepository.deleteById(reviewId);
//            return true;
//        }
//        return false;
//    }
//}

    @Override
    public Boolean deleteReview(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
