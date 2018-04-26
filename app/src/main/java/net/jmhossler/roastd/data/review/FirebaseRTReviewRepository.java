package net.jmhossler.roastd.data.review;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.jmhossler.roastd.data.bean.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseRTReviewRepository implements ReviewDataSource {

  private static final String TAG = "FirebaseRTReviewRepository";
  private static FirebaseRTReviewRepository sInstance = null;
  private static DatabaseReference mDatabase;

  public static FirebaseRTReviewRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTReviewRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  private Review populateNullFields(Review r) {
    if (r.getUuid() == null) {
      Log.e(TAG, "Error: Review UUID is null!");
    }
    if (r.getUserUuid() == null) {
      r.setUserUuid("");
    }
    return r;
  }

  @Override
  public void getReview(String reviewId, GetReviewCallback callback) {
    mDatabase.child("reviews/" + reviewId).addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          Review r = dataSnapshot.getValue(Review.class);
          callback.onReviewLoaded(r != null ? populateNullFields(r) : r);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void getReviews(LoadReviewsCallback callback) {
    mDatabase.child("reviews").addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          List<Review> reviews = new ArrayList<>();
          dataSnapshot.getChildren().forEach(r -> reviews.add(r.getValue(Review.class)));
          callback.onReviewsLoaded(reviews);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void saveReview(Review review) {
    mDatabase.child("reviews").child(review.getUuid()).setValue(review);
  }

  @Override
  public void deleteReview(String reviewId) {
    mDatabase.child("reviews").child(reviewId).removeValue();
  }
}
