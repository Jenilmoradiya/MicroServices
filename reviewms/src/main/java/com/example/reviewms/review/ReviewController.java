package com.example.reviewms.review;

import com.example.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/Companies/{companyId}")
@RequestMapping("/reviews")
public class ReviewController {

   private ReviewService reviewService;
   private ReviewMessageProducer reviewMessageProducer;


    public ReviewController(ReviewService reviewService,ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }


    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
        Boolean isreviewsaved=reviewService.AddReview(companyId, review);
        if(isreviewsaved) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Review not added",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {

        Boolean isupdated =reviewService.updateReview(reviewId, review);
        if(isupdated)
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not updated",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deletereview(@PathVariable Long reviewId){
        boolean isdeleted = reviewService.deleteReview(reviewId);
        if(isdeleted)
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not deleted",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId) {
        List<Review> reviewList= reviewService.getReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}

