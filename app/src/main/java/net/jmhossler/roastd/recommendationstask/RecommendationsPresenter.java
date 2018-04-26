package net.jmhossler.roastd.recommendationstask;

import com.google.firebase.auth.FirebaseAuth;

import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.data.user.UserDataSource;
import net.jmhossler.roastd.viewtask.BaseSearchableItemPresenter;
import net.jmhossler.roastd.viewtask.SearchableItemListContract;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsPresenter extends BaseSearchableItemPresenter {

  public RecommendationsPresenter(SearchableItemListContract.View v, FirebaseAuth firebaseAuth,
                            SearchableItemDataSource searchableItemRepository,
                            UserDataSource userRepository) {
    super(v, firebaseAuth, searchableItemRepository, userRepository);
  }

  @Override
  public void userLoaded() {
    retrieveRecommendations();
  }

  public void retrieveRecommendations() {
    List<String> recommendationsList = new ArrayList<>(mUser.getRecommendationUUIDs().keySet());

    mSearchableItemDataStore.getSearchableItems(recommendationsList, new SearchableItemDataSource.LoadSearchableItemsCallback() {
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
