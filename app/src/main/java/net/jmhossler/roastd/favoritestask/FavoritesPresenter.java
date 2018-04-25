package net.jmhossler.roastd.favoritestask;

import net.jmhossler.roastd.data.searchableItem.FirebaseRTSearchableItemRepository;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.searchableItem.SearchableItemDataSource;
import net.jmhossler.roastd.viewtask.BaseSearchableItemPresenter;
import net.jmhossler.roastd.viewtask.SearchableItemListContract;

import java.util.ArrayList;
import java.util.List;

public class FavoritesPresenter extends BaseSearchableItemPresenter {

  public FavoritesPresenter(SearchableItemListContract.View v) {
    super(v);
    mListView.setPresenter(this);

    // example change this to favorites using john's wrapper
    SearchableItemDataSource ds = FirebaseRTSearchableItemRepository.getInstance();

    ArrayList <String> list = new ArrayList<>();
    list.add("12346844634987");
    list.add("12346844634972");

    ds.getSearchableItems(list, new SearchableItemDataSource.LoadSearchableItemsCallback() {
      @Override
      public void onSearchableItemsLoaded(List<SearchableItem> items) {
        mItems = items;
        mListView.notifyDataSetChanged();
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }
}
