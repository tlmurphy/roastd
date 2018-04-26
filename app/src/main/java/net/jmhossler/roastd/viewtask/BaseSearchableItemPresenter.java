package net.jmhossler.roastd.viewtask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import net.jmhossler.roastd.data.bean.Bean;
import net.jmhossler.roastd.data.drink.Drink;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.data.shop.Shop;
import net.jmhossler.roastd.data.user.User;
import net.jmhossler.roastd.data.user.UserDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseSearchableItemPresenter implements SearchableItemListContract.Presenter {

  private static final String TAG = "BaseSearchableItemPresenter";
  protected SearchableItemListContract.View mListView;
  private List<SearchableItem> mItems;
  protected UserDataSource mUserDataStore;
  protected SearchableItemDataSource mSearchableItemDataStore;
  protected FirebaseAuth mAuth;
  protected User mUser;
  protected List<AsyncTask> mDownloaders;

  public BaseSearchableItemPresenter(SearchableItemListContract.View v, FirebaseAuth firebaseAuth,
                                     SearchableItemDataSource searchableItemRepository,
                                     UserDataSource userRepository) {
    mListView = v;
    mListView.setPresenter(this);
    setItems(new ArrayList<>());
    mAuth = firebaseAuth;
    mSearchableItemDataStore = searchableItemRepository;
    mUserDataStore = userRepository;
    mDownloaders = new ArrayList<>();
  }

  @Override
  public void onListItemClicked(int position) {
    SearchableItem i = getItems().get(position);
    if (i instanceof Drink) {
      mListView.navigateToDrink(i.getUuid());
    } else if (i instanceof Shop) {
      mListView.navigateToShop(i.getUuid());
    } else if (i instanceof Bean) {
      mListView.navigateToBean(i.getUuid());
    } else {
      // oh noes
    }
  }

  public void loadAllImages() {
    for (int i = 0; i < getItems().size(); ++i) {
      mDownloaders.add(new DownloadImageTask(i, getItems().get(i).getUuid(), i)
      .execute(getItems().get(i).getImage()));
    }
  }

  public void cancelImageLoads() {
    for (AsyncTask t : mDownloaders) {
      t.cancel(true);
    }
    mDownloaders.clear();
  }

  @Override
  public void toggleFavorite(int position, Boolean isFavoriting) {
    if (isFavoriting) {
      mUser.addFavoriteUUID(getItems().get(position).getUuid());
    } else {
      mUser.removeFavoriteUUID(getItems().get(position).getUuid());
    }
    mUserDataStore.saveUser(mUser);
  }

  @Override
    public void bindViewAtPosition(int position, SearchableItemListContract.SearchableListItemView view,
                                   List<Object> payloads) {
    if (payloads != null && payloads.size() != 0) {
      if (position >= getItems().size()) {
        Log.w(TAG, "Downloader delivered image to nonexistent position!");
        return;
      }
      Pair<Bitmap, String> p = (Pair<Bitmap, String>) payloads.get(0);
      if (p.second.equals(getItems().get(position).getUuid())) {
        view.setIcon(p.first);
      } else {
        Log.w(TAG, "Downloader delivered image to existent position, but it has the wrong UUID!");
        return;
      }
    }

    SearchableItem si = getItems().get(position);
    view.setContent(si.getName());
    view.setFavoriteState(mUser.getFavoriteUUIDs().containsKey(si.getUuid()));
  }

  @Override
  public int itemCount() {
    return getItems().size();
  }

  @Override
  public void destroy() {
    cancelImageLoads();
  }

  protected void setItems(List<SearchableItem> items) {
    if (items == null) {
      mItems.clear();
    } else {
      mItems = items;
    }
  }

  protected List<SearchableItem> getItems(){
    return mItems;
  }

  public void userLoaded() {

  }

  @Override
  public void start() {
    mUserDataStore.getUser(mAuth.getUid(), new UserDataSource.GetUserCallback() {
      @Override
      public void onUserLoaded(User user) {
        mUser = user;
        userLoaded();
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "DownloadImageTask";
    private String uuid;
    private int position;
    private int downloaderNumber;

    public DownloadImageTask(int position, String uuid, int downloaderNum) {
      this.position = position;
      this.uuid = uuid;
      downloaderNumber = downloaderNum;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
      if (urls.length == 0) {
        Log.e(TAG, "Did not receive any urls!");
        return null;
      }
      if (urls.length != 1) {
        Log.w(TAG, "Was only expecting one URL, but will continue processing just the first one");
      }
      String urlDisplay = urls[0];
      Bitmap mIcon11 = null;
      InputStream in = null;

      if (urlDisplay.isEmpty()) {
        Log.i(TAG, "Item has no image URL, skipping...");
        return null;
      }

      try {
        // For optimization, stop now if this thread is already cancelled
        if (isCancelled()) {
          return null;
        }
        in = new java.net.URL(urlDisplay).openStream();
        mIcon11 = BitmapFactory.decodeStream(in);
      } catch (Exception e) {
        Log.e(TAG, "Error attempting to download '" + urlDisplay + "': " + e.getMessage());
        e.printStackTrace();
      } finally {
        if (in != null) {
          try {
            in.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      return mIcon11;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      if (bitmap != null) {
        BaseSearchableItemPresenter.this.mListView.
          notifyItemChanged(position, new Pair<>(bitmap, uuid));
      }
    }

    @Override
    protected void onCancelled() {
      Log.i(TAG, "Thread " + downloaderNumber + ": cancelled!");
    }
  }
}
