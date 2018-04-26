package net.jmhossler.roastd.data.user;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseRTUserRepository implements UserDataSource {

  private static final String TAG = "FirebaseRTUserRepository";
  private static FirebaseRTUserRepository sInstance = null;
  private static DatabaseReference mDatabase;

  public static FirebaseRTUserRepository getsInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTUserRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  private User populateNullFields(User u) {
    if (u.getUuid() == null) {
      Log.e(TAG, "Error: User UUID is null!");
    }
    if (u.getEmail() == null) {
      u.setEmail("");
    }
    if (u.getName() == null) {
      u.setName("");
    }
    if (u.getPhotoURL() == null) {
      u.setPhotoURL("");
    }
    if (u.getFavoriteUUIDs() == null) {
      u.setFavoriteUUIDs(new HashMap<>());
    }
    if (u.getRecommendationUUIDs() == null) {
      u.setRecommendationUUIDs(new HashMap<>());
    }
    return u;
  }

  @Override
  public void getUser(String userId, GetUserCallback callback) {
    mDatabase.child("users/" + userId).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        User u = dataSnapshot.getValue(User.class);
        callback.onUserLoaded(u != null ? populateNullFields(u) : u);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        callback.onDataNotAvailable();
      }
    });
  }

  @Override
  public void saveUser(User user) {
    mDatabase.child("users").child(user.getUuid()).setValue(user);
  }
}
