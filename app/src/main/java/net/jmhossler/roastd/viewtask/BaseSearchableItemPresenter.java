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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseSearchableItemPresenter implements SearchableItemListContract.Presenter {

  private static final String TAG = "BaseSearchableItemPresenter";
  protected SearchableItemListContract.View mListView;
  protected List<SearchableItem> mItems;
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
    mItems = new ArrayList<>();
    mAuth = firebaseAuth;
    mSearchableItemDataStore = searchableItemRepository;
    mUserDataStore = userRepository;
    mDownloaders = new ArrayList<>();
  }

  @Override
  public void onListItemClicked(int position) {
    SearchableItem i = mItems.get(position);
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
    for (int i = 0; i < mItems.size(); ++i) {
      mDownloaders.add(new DownloadImageTask(i, mItems.get(i).getUuid())
      .execute(mItems.get(i).getImage()));
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
      mUser.addFavoriteUUID(mItems.get(position).getUuid());
    } else {
      mUser.removeFavoriteUUID(mItems.get(position).getUuid());
    }
    mUserDataStore.saveUser(mUser);
  }

  @Override
    public void bindViewAtPosition(int position, SearchableItemListContract.SearchableListItemView view,
                                   List<Object> payloads) {
    if (payloads != null && payloads.size() != 0) {
      if (position >= mItems.size()) {
        Log.w(TAG, "Downloader delivered image to nonexistent position!");
        return;
      }
      Pair<Bitmap, String> p = (Pair<Bitmap, String>) payloads.get(0);
      if (p.second == mItems.get(position).getUuid()) {
        view.setIcon(p.first);
      } else {
        Log.w(TAG, "Downloader delivered image to existent position, but it has the wrong UUID!");
        return;
      }
    }

    SearchableItem si = mItems.get(position);
    view.setContent(si.getName());
    view.setFavoriteState(mUser.getFavoriteUUIDs().containsKey(si.getUuid()));
  }

  @Override
  public int itemCount() {
    return mItems.size();
  }

  @Override
  public void destroy() {
    cancelImageLoads();
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
    String uuid;
    int position;

    public DownloadImageTask(int position, String uuid) {
      this.position = position;
      this.uuid = uuid;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
      String urldisplay = urls[0];
      Bitmap mIcon11 = null;

      try {
        InputStream in = new java.net.URL(urldisplay).openStream();
        mIcon11 = BitmapFactory.decodeStream(in);
      } catch (Exception e) {
        Log.e("Error", e.getMessage());
        e.printStackTrace();
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
  }
}
