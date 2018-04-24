package net.jmhossler.roastd.viewtask;

import net.jmhossler.roastd.data.bean.Bean;
import net.jmhossler.roastd.data.drink.Drink;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;
import net.jmhossler.roastd.data.shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class BaseSearchableItemPresenter implements SearchableItemListContract.Presenter {

  protected SearchableItemListContract.View mListView;
  protected List<SearchableItem> mItems;

  public BaseSearchableItemPresenter(SearchableItemListContract.View v) {
    mListView = v;
    mListView.setPresenter(this);
    mItems = new ArrayList<>();
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

  @Override
  public void bindViewAtPosition(int position, SearchableItemListContract.SearchableListItemView view) {
    view.setContent(mItems.get(position).getDescription());
  }

  @Override
  public int itemCount() {
    return mItems.size();
  }

  @Override
  public void start() {

  }
}
