package net.jmhossler.roastd.data.review;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class FirebaseRTReviewRepository implements ReviewDataSource {

  private static FirebaseRTReviewRepository sInstance = null;
  private static DatabaseReference mDatabase;

  public static FirebaseRTReviewRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTReviewRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  @Override
  public void getReview(String reviewId, GetReviewCallback callback) {
    mDatabase.child("reviews/" + reviewId).addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          callback.onReviewLoaded(dataSnapshot.getValue(Review.class));
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
