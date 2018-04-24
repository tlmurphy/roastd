package net.jmhossler.roastd.data.user;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseRTUserRepository implements UserDataSource {

  private static FirebaseRTUserRepository sInstance = null;
  private static DatabaseReference mDatabase;

  public static FirebaseRTUserRepository getsInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTUserRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  @Override
  public void getUser(String userId, GetUserCallback callback) {
    mDatabase.child("users/" + userId).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        callback.onUserLoaded(dataSnapshot.getValue(User.class));
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
