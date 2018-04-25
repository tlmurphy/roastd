package net.jmhossler.roastd.data.searchableItem;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;

import net.jmhossler.roastd.data.bean.FirebaseRTBeanRepository;
import net.jmhossler.roastd.data.drink.FirebaseRTDrinkRepository;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    final int SHOP = 0;
    final int DRINK = 1;
    final int BEAN = 2;

    List<TaskCompletionSource<SearchableItem>> queries = new ArrayList<>();
    queries.add(new TaskCompletionSource<>());
    queries.add(new TaskCompletionSource<>());
    queries.add(new TaskCompletionSource<>());

    getItemFromRepository(mShopRepository, itemId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        queries.get(SHOP).setResult(item);
      }

      @Override
      public void onDataNotAvailable() {
        queries.get(SHOP).setException(new IOException("Could not get shop data from repository"));
      }
    });

    getItemFromRepository(mDrinkRepository, itemId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        queries.get(DRINK).setResult(item);
      }

      @Override
      public void onDataNotAvailable() {
        queries.get(DRINK).setException(new IOException("Could not get drink data from repository"));
      }
    });

    getItemFromRepository(mBeanRepository, itemId, new BaseDataSource.GetCallback() {
      @Override
      public void onLoaded(SearchableItem item) {
        queries.get(BEAN).setResult(item);
      }

      @Override
      public void onDataNotAvailable() {
        queries.get(BEAN).setException(new IOException("Could not get bean data from repository"));
      }
    });

    List <Task<SearchableItem>> tasks =
      queries.stream()
        .map(tcs -> tcs.getTask())
        .collect(Collectors.toList());

    Task<Void> all = Tasks.whenAll(tasks);
    all.addOnSuccessListener(voidTask -> {
      List<SearchableItem> results =
        tasks.stream()
          .map(task -> task.getResult())
          .filter(item -> item != null)
          .collect(Collectors.toList());

      if (results.size() == 0) {
        callback.onLoaded(null);
      } else if (results.size() == 1) {
        callback.onLoaded(results.get(0));
      } else {
        // well shit!
        Log.d(TAG, itemId + ": Multiple instances found in multiple repositories");
      }
    });
    all.addOnFailureListener(e -> {
      callback.onDataNotAvailable();
      Log.e(TAG, e.getMessage());
    });
  }

  @Override
  public void getSearchableItems(List <String> ids, LoadSearchableItemsCallback callback) {
    List<TaskCompletionSource<SearchableItem>> queries = new ArrayList<>();

    for (String id : ids) {
      TaskCompletionSource<SearchableItem> ct = new TaskCompletionSource<>();
      get(id, new GetCallback() {
        @Override
        public void onLoaded(SearchableItem item) {
          ct.setResult(item);
        }

        @Override
        public void onDataNotAvailable() {
          ct.setException(new IOException("Could not retrieve searchable item..."));
        }
      });
      queries.add(ct);
    }

    List <Task<SearchableItem>> tasks =
      queries.stream()
        .map(tcs -> tcs.getTask())
        .collect(Collectors.toList());

    Task<Void> all = Tasks.whenAll(tasks);
    all.addOnSuccessListener(voidTask -> {
      List<SearchableItem> results =
        tasks.stream()
          .map(task -> task.getResult())
          .filter(item -> item != null)
          .collect(Collectors.toList());

      if (results.size() == 0) {
        callback.onSearchableItemsLoaded(null);
      }

      if (results.size() != ids.size()) {
        Log.w(TAG, "We didn't get all we were looking for...");
      }

      callback.onSearchableItemsLoaded(results);
    });
    all.addOnFailureListener(e -> {
      callback.onDataNotAvailable();
      Log.e(TAG, e.getMessage());
    });
  }
  private void getItemFromRepository(FirebaseRTBaseRepository repository, String itemId, BaseDataSource.GetCallback callback) {
    repository.get(itemId, callback);
  }
}
