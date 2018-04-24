package net.jmhossler.roastd.data.shop;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import net.jmhossler.roastd.data.searchableItem.FirebaseRTBaseRepository;

public class FirebaseRTShopRepository extends FirebaseRTBaseRepository implements ShopDataSource {

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
  public void get(String shopId, GetCallback callback) {
    mDatabase.child("shops/" + shopId).addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          callback.onLoaded(dataSnapshot.getValue(Shop.class));
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
          List<Shop> shops = new ArrayList<>();
          dataSnapshot.getChildren().forEach(s -> shops.add(s.getValue(Shop.class)));
          callback.onShopsLoaded(shops);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void saveShop(Shop shop) {
    mDatabase.child("shops").child(shop.getUuid()).setValue(shop, (databaseError, databaseReference) -> {
      if (databaseError != null) {

      } else {

      }
    });
  }

  @Override
  public void deleteShop(String shopId) {
    mDatabase.child("shops").child(shopId).removeValue();
  }
}
