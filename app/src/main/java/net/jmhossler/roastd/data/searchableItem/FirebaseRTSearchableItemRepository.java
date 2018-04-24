package net.jmhossler.roastd.data.searchableItem;

import android.util.Log;

import net.jmhossler.roastd.data.bean.FirebaseRTBeanRepository;
import net.jmhossler.roastd.data.drink.FirebaseRTDrinkRepository;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;

import static android.support.constraint.Constraints.TAG;

public class FirebaseRTSearchableItemRepository extends FirebaseRTBaseRepository implements SearchableItemDataSource {
  private static FirebaseRTSearchableItemRepository sInstance = null;
  private static FirebaseRTShopRepository mShopRepository = null;
  private static FirebaseRTBeanRepository mBeanRepository = null;
  private static FirebaseRTDrinkRepository mDrinkRepository = null;


  public static FirebaseRTSearchableItemRepository getInstance() {
    if (sInstance == null) {
      sInstance = new FirebaseRTSearchableItemRepository();
      mShopRepository = FirebaseRTShopRepository.getInstance();
      mBeanRepository = FirebaseRTBeanRepository.getInstance();
      mDrinkRepository = FirebaseRTDrinkRepository.getInstance();
    }
    return sInstance;
  }


  @Override
  public void get(String itemId, GetCallback callback) {
    getItemFromRepository(mShopRepository, itemId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        callback.onLoaded(item);
      }

      @Override
      public void onDataNotAvailable() {
        getItemFromRepository(mBeanRepository, itemId, new BaseDataSource.GetCallback() {
          @Override
          public void onLoaded(SearchableItem item) {
            callback.onLoaded(item);
          }

          @Override
          public void onDataNotAvailable() {
            getItemFromRepository(mDrinkRepository, itemId, new BaseDataSource.GetCallback() {
              @Override
              public void onLoaded(SearchableItem item) {
                callback.onLoaded(item);
              }

              @Override
              public void onDataNotAvailable() {
                Log.d(TAG, itemId + ": Not found in repository");
              }
            });
          }
        });
      }
    });
  }

  private void getItemFromRepository(FirebaseRTBaseRepository repository, String itemId, BaseDataSource.GetCallback callback) {
    repository.get(itemId, callback);
  }
}
