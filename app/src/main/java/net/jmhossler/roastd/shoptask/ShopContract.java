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
    void displayMapsUrl(String url);
    void displayConsumeables(List<SearchableItem> items);
    void displayRating(int score);
    void finish();
  }

  interface Presenter extends BasePresenter {
    void setConsumeables();
    void setName();
    void setDescription();
    void setAddress();
    void setMapsUrl();
    void setCurrentRating();
    void setNewRating(float rating);
  }
}
