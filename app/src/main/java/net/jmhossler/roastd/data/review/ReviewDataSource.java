package net.jmhossler.roastd.data.review;

import java.util.List;

public interface ReviewDataSource {

  interface GetReviewCallback {

    void onReviewLoaded(Review review);

    void onDataNotAvailable();
  }

  interface LoadReviewsCallback {

    void onReviewsLoaded(List<Review> reviews);

    void onDataNotAvailable();
  }

  void getReview(String reviewId, GetReviewCallback callback);

  void getReviews(LoadReviewsCallback callback);

  void saveReview(Review review);

  void deleteReview(String reviewId);
}
