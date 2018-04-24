package net.jmhossler.roastd.data.review;

import java.util.Map;

public interface ReviewDataSource {

  interface GetReviewCallback {

    void onReviewLoaded(Review review);

    void onDataNotAvailable();
  }

  interface LoadReviewsCallback {

    void onReviewsLoaded(Map<String, Review> reviews);

    void onDataNotAvailable();
  }

  void getReview(String reviewId, GetReviewCallback callback);

  void getReviews(LoadReviewsCallback callback);

  void saveReview(Review review);
}
