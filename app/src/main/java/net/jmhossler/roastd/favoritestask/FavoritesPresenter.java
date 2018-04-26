package net.jmhossler.roastd.favoritestask;

import com.google.firebase.auth.FirebaseAuth;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.data.user.UserDataSource;
import net.jmhossler.roastd.viewtask.BaseSearchableItemPresenter;
import net.jmhossler.roastd.viewtask.SearchableItemListContract;
import java.util.List;
import java.util.ArrayList;

public class FavoritesPresenter extends BaseSearchableItemPresenter {

  public FavoritesPresenter(SearchableItemListContract.View v, FirebaseAuth firebaseAuth,
                            SearchableItemDataSource searchableItemRepository,
                            UserDataSource userRepository) {
    super(v, firebaseAuth, searchableItemRepository, userRepository);
  }

  @Override
  public void userLoaded() {
    populateUserFavorites();
  }

  public void populateUserFavorites() {
    List<String> favoritesList = new ArrayList<>(mUser.getFavoriteUUIDs().keySet());

    mSearchableItemDataStore.getSearchableItems(favoritesList, new SearchableItemDataSource.LoadSearchableItemsCallback() {
      @Override
      public void onSearchableItemsLoaded(List<SearchableItem> items) {
        mItems = items;
        mListView.notifyDataSetChanged();
        mListView.hideProgressBarShowList();
      }

      @Override
      public void onDataNotAvailable() {
        // panic
      }
    });
  }
}
