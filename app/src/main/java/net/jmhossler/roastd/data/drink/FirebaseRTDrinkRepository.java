package net.jmhossler.roastd.data.drink;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class FirebaseRTDrinkRepository implements DrinkDataSource {

  private static FirebaseRTDrinkRepository sInstance = null;
  private static DatabaseReference mDatabase;


  public static FirebaseRTDrinkRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTDrinkRepository();
      mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    return sInstance;
  }

  @Override
  public void getDrink(String drinkId, GetDrinkCallback callback) {
    mDatabase.child("drinks/" + drinkId).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        callback.onDrinkLoaded(dataSnapshot.getValue(Drink.class));
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        callback.onDataNotAvailable();
      }
    });
  }

  @Override
  public void getDrinks(LoadDrinksCallback callback) {
    mDatabase.child("drinks").addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          callback.onDrinksLoaded((Map<String, Drink>) dataSnapshot.getValue());
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          callback.onDataNotAvailable();
        }
      });
  }

  @Override
  public void saveDrink(Drink drink) {
    mDatabase.child("drinks").child(drink.getUuid()).setValue(drink);
  }
}
