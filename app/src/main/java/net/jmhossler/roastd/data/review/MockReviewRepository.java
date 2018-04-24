package net.jmhossler.roastd.data.review;

import net.jmhossler.roastd.data.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static net.jmhossler.roastd.data.MockDataSourceUtils.deepClone;

public class MockReviewRepository implements ReviewDataSource {

  private static MockReviewRepository sInstance = null;
  private static ArrayList<Review> sReviews;

  private MockReviewRepository() {

  }

  public static MockReviewRepository getInstance() {
    if (sInstance == null) {
      sInstance = new MockReviewRepository();
      sReviews = new ArrayList<>();
    }
    return sInstance;
  }

  @Override
  public void getReview(UUID reviewId, GetReviewCallback callback) {
    Optional<Review> op = sReviews.stream()
      .filter(review -> review.getUuid() == reviewId)
      .findFirst();

    if (op.isPresent()) {
      // Using cloning to try to emulate repository behavior
      callback.onReviewLoaded(deepClone(op.get()));
    } else {
      callback.onDataNotAvailable();
    }
  }

  @Override
  public void getReviews(LoadReviewsCallback callback) {
    // Using cloning to try to emulate repository behavior
    callback.onReviewsLoaded(deepClone(sReviews));
  }

  @Override
  public void saveReview(Review review) {
    int index =
      IntStream.range(0, sReviews.size())
        .filter(i -> sReviews.get(i).getUuid() == review.getUuid())
        .findFirst()
        .orElse(-1);

    // Using cloning to try to emulate repository behavior
    Review copy = deepClone(review);

    if (index == -1) {
      sReviews.add(copy);
    } else {
      sReviews.set(index, copy);
    }
  }
}
