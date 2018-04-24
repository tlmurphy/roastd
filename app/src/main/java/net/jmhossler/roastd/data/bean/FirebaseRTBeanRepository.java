package net.jmhossler.roastd.data.bean;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class FirebaseRTBeanRepository implements BeanDataSource {

  private static FirebaseRTBeanRepository sInstance = null;
  private static DatabaseReference mDatabase;

  public static FirebaseRTBeanRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTBeanRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  @Override
  public void getBean(String beanId, GetBeanCallback callback) {
    mDatabase.child("beans/" + beanId).addListenerForSingleValueEvent(
      new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        callback.onBeanLoaded(dataSnapshot.getValue(Bean.class));
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        callback.onDataNotAvailable();
      }
    });
  }

  @Override
  public void getBeans(LoadBeansCallback callback) {
    mDatabase.child("beans").addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          callback.onBeansLoaded((Map<String, Bean>) dataSnapshot.getValue());
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void saveBean(Bean bean) {
   mDatabase.child("beans").child(bean.getUuid().toString()).setValue(bean);
  }
}
