package net.jmhossler.roastd.data.shop;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class FirebaseRTShopRepository implements ShopDataSource {

  private static FirebaseRTShopRepository sInstance = null;
  private static DatabaseReference mDatabase;


  public static FirebaseRTShopRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTShopRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  @Override
  public void getShop(String shopId, GetShopCallback callback) {
    mDatabase.child("shops/" + shopId).addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          callback.onShopLoaded(dataSnapshot.getValue(Shop.class));
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void getShops(LoadShopsCallback callback) {
    mDatabase.child("shops").addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          callback.onShopsLoaded((Map<String, Shop>) dataSnapshot.getValue());
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void saveShop(Shop shop) {
    mDatabase.child("shops").child(shop.getUuid()).setValue(shop, new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        if (databaseError != null) {

        } else {

        }
      }
    });
  }

  @Override
  public void deleteShop(String shopId) {
    mDatabase.child("shops").child(shopId).removeValue();
  }
}
