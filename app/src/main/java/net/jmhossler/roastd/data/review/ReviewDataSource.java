package net.jmhossler.roastd.data.review;

import java.util.List;
import java.util.UUID;

public interface ReviewDataSource {

  interface GetReviewCallback {

    void onReviewLoaded(Review review);

    void onDataNotAvailable();
  }

  interface LoadReviewsCallback {

    void onReviewsLoaded(List<Review> reviews);

    void onDataNotAvailable();
  }

  void getReview(UUID reviewId, GetReviewCallback callback);

  void getReviews(LoadReviewsCallback callback);

  void saveReview(Review review);
}
