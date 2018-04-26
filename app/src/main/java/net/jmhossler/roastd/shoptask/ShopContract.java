package net.jmhossler.roastd.shoptask;

import net.jmhossler.roastd.BasePresenter;
import net.jmhossler.roastd.BaseView;
import net.jmhossler.roastd.data.searchableItem.SearchableItem;

import java.util.List;

public interface ShopContract {
  interface View extends BaseView<Presenter> {
    void displayName(String name);
    void displayDescription(String description);
    void displayAddress(String address);
    void displayImage(String imageUrl);
    void createMapsLink(String url);
    void displayConsumeables(List<SearchableItem> items);
    void displayRating(int score);
    void finish();
  }

  interface Presenter extends BasePresenter {
    void setConsumeables();
    void setName(SearchableItem item);
    void setImage(SearchableItem item);
    void setDescription(SearchableItem item);
    void setAddress(SearchableItem item);
    void setMapsUrl(SearchableItem item);
    void setCurrentRating(SearchableItem item);
    void setNewRating(float rating);
  }
}
