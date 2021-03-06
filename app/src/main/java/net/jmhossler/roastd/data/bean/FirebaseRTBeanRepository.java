package net.jmhossler.roastd.data.bean;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTBaseRepository;

public class FirebaseRTBeanRepository extends FirebaseRTBaseRepository implements BeanDataSource {

  private static final String TAG = "FirebaseRTBeanRepository";
  private static FirebaseRTBeanRepository sInstance = null;
  private static DatabaseReference mDatabase;

  public static FirebaseRTBeanRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTBeanRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  private Bean populateNullFields(Bean b) {
    super.populateNullFields(b);
    if (b.getRoastType() == null) {
      b.setRoastType("");
    }
    if (b.getOrigin() == null) {
      b.setOrigin("");
    }
    return b;
  }

  @Override
  public void get(String beanId, GetCallback callback) {
    mDatabase.child("beans/" + beanId).addListenerForSingleValueEvent(
      new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        Bean b = dataSnapshot.getValue(Bean.class);
        callback.onLoaded(b != null ? populateNullFields(b) : b);
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
          List<Bean> beans = new ArrayList<>();
          dataSnapshot.getChildren().forEach(b -> beans.add(b.getValue(Bean.class)));
          callback.onBeansLoaded(beans);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void saveBean(Bean bean) {
   mDatabase.child("beans").child(bean.getUuid()).setValue(bean);
  }
}
