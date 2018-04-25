package net.jmhossler.roastd.data.review;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
          callback.onReviewsLoaded((Map<String, Review>) dataSnapshot.getValue());
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
}
